package com.example.adminshriparvatioilandproduct

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.adminshriparvatioilandproduct.R
import com.example.adminshriparvatioilandproduct.adpater.AddItemAdapter
import com.example.adminshriparvatioilandproduct.databinding.ActivityAllItemBinding

class AllItemActivity : AppCompatActivity() {
    private val binding: ActivityAllItemBinding by lazy {
        ActivityAllItemBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        val menuProductName= listOf("Sunflower","Coconut","Soyabeen","Groundnut","Flaxseed","Seal")
        val menuItemPrice= listOf("₹5","₹6","₹7","₹5","₹6","₹7",)
        val menuImage= listOf(R.drawable.product1,R.drawable.product2,R.drawable.product2,R.drawable.product1,R.drawable.product2,R.drawable.product1)
        binding.backButton.setOnClickListener {
            finish()
        }

        val adapter=AddItemAdapter(ArrayList(menuProductName),ArrayList(menuItemPrice),ArrayList(menuImage))

        binding.MenuRecyclerView.layoutManager=LinearLayoutManager(this)
        binding.MenuRecyclerView.adapter=adapter

        }
    }
