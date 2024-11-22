package com.example.adminshriparvatioilandproduct

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminshriparvatioilandproduct.adpater.AddItemAdapter
import com.example.adminshriparvatioilandproduct.databinding.ActivityAllItemBinding
import com.google.firebase.database.*

class AllItemActivity : AppCompatActivity() {
    private val binding: ActivityAllItemBinding by lazy {
        ActivityAllItemBinding.inflate(layoutInflater)
    }

    private val databaseReference: DatabaseReference by lazy {
        FirebaseDatabase.getInstance().getReference("Products")
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        binding.backButton.setOnClickListener {
            finish()
        }

        // Initialize RecyclerView
        binding.MenuRecyclerView.layoutManager = LinearLayoutManager(this)

        fetchDataFromFirebase()
    }

    private fun fetchDataFromFirebase() {
        databaseReference.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                val menuProductName = mutableListOf<String>()
                val menuItemPrice = mutableListOf<String>()

                for (itemSnapshot in snapshot.children) {
                    val name = itemSnapshot.child("name").value as? String ?: "Unnamed"
                    val price = itemSnapshot.child("price").value as? Long ?: 0L // Handle price as Long

                    menuProductName.add(name)
                    menuItemPrice.add("₹$price") // Convert to String with currency symbol
                }

                // Update RecyclerView adapter
                val adapter = AddItemAdapter(menuProductName, menuItemPrice, mutableListOf())
                binding.MenuRecyclerView.adapter = adapter
            }

            override fun onCancelled(error: DatabaseError) {
                Toast.makeText(this@AllItemActivity, "Failed to load data.", Toast.LENGTH_SHORT).show()
            }
        })
    }
}
