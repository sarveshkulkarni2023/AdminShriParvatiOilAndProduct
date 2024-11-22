package com.example.adminshriparvatioilandproduct

import android.content.Intent
import android.os.Bundle
import android.widget.ArrayAdapter
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import com.example.adminshriparvatioilandproduct.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {
    private  val binding: ActivitySignUpBinding by lazy {
        ActivitySignUpBinding.inflate(layoutInflater)
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)
        binding.createAccountButton.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)


        }
        binding.alreadyHaveAccountButton.setOnClickListener {
            val intent = Intent(this, LoginActivity::class.java)
            startActivity(intent)
        }
        val locationlist= arrayOf("Nashik","Ahmadnagar","Yeola","Kopargaon")
        val adapter= ArrayAdapter(this,android.R.layout.simple_list_item_1,locationlist)
        val autoCompleteTextView=binding.listoflocation
        autoCompleteTextView.setAdapter(adapter)

        }
    }
