package com.example.adminshriparvatioilandproduct

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import com.example.adminshriparvatioilandproduct.databinding.ActivityAddItemBinding


class AddItemActivity : AppCompatActivity() {
    private val binding: ActivityAddItemBinding by lazy {
        ActivityAddItemBinding.inflate(layoutInflater)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.selectImage.setOnClickListener {
            pickimage.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))


        }
        binding.backButton.setOnClickListener {
            finish()
        }

    }

    val pickimage = registerForActivityResult(ActivityResultContracts.PickVisualMedia()) { uri ->
        if (uri != null) {
            binding.selectedImage.setImageURI(uri)
        }
    }
}