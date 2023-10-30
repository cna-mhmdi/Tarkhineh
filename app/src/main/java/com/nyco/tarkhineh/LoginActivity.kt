package com.nyco.tarkhineh

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import com.nyco.tarkhineh.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendCode.isEnabled = false
        val phonePattern = "^09\\d{9}$".toRegex()

        binding.editTextPhoneNumber.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

            override fun afterTextChanged(p0: Editable?) {

                val phoneNumber = p0.toString()
                val isChecked = binding.checkboxPrivacy.isChecked
                val isValidPhoneNumber = phonePattern.matches(phoneNumber)
                binding.btnSendCode.isEnabled = isChecked && isValidPhoneNumber

            }
        })

        binding.checkboxPrivacy.setOnCheckedChangeListener { _, isChecked ->

            val isValidPhoneNumber = phonePattern.matches(binding.editTextPhoneNumber.text.toString())
            binding.btnSendCode.isEnabled = isChecked && isValidPhoneNumber
        }


    }
}