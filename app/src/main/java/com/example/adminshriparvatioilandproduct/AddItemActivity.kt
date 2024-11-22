package com.example.adminshriparvatioilandproduct

import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminshriparvatioilandproduct.databinding.ActivityAddItemBinding
import com.google.firebase.database.FirebaseDatabase

class AddItemActivity : AppCompatActivity() {
    private val binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(layoutInflater)
    }
    private var selectedImageUri: Uri? = null // Store selected image URI

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        // Initialize Firebase Database reference
        val database = FirebaseDatabase.getInstance()
        val productsRef = database.getReference("Products")

        // Handle image selection
        binding.selectImage.setOnClickListener {
            pickImage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
        }

        // Add item to Firebase
        binding.AddItemButton.setOnClickListener {
            val productName = binding.enterproductname.text.toString().trim()
            val productPrice = binding.enterproductprice.text.toString().trim()
            val productDescription = binding.description.text.toString().trim()
            val productIngredients = binding.ingredient.text.toString().trim()

            if (productName.isNotEmpty() && productPrice.isNotEmpty() &&
                productDescription.isNotEmpty() && productIngredients.isNotEmpty()
            ) {
                val productId = productsRef.push().key // Generate unique key for the product
                val product = Product(
                    name = productName,
                    price = productPrice.toInt(),
                    description = productDescription,
                    ingredients = productIngredients
                )

                productId?.let {
                    productsRef.child(it).setValue(product)
                        .addOnSuccessListener {
                            Toast.makeText(this, "Product added successfully!", Toast.LENGTH_SHORT).show()
                            clearFields()
                        }
                        .addOnFailureListener {
                            Toast.makeText(this, "Failed to add product.", Toast.LENGTH_SHORT).show()
                        }
                }
            } else {
                Toast.makeText(this, "Please fill all fields.", Toast.LENGTH_SHORT).show()
            }
        }

        binding.backButton.setOnClickListener {
            finish()
        }
    }

    private val pickImage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            selectedImageUri = uri // Update the UI with the selected image
            binding.selectedImage.setImageURI(uri)
        }
    }

    private fun clearFields() {
        binding.enterproductname.text.clear()
        binding.enterproductprice.text.clear()
        binding.description.text.clear()
        binding.ingredient.text.clear()
        binding.selectedImage.setImageResource(R.drawable.addimage) // Reset the image
        selectedImageUri = null
    }

    // Product data model matching the Firebase structure
    data class Product(
        val name: String,
        val price: Int,
        val description: String,
        val ingredients: String
    )
}
