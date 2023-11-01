package com.nyco.tarkhineh

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.databinding.ActivityLoginBinding

class LoginActivity : AppCompatActivity() {

    companion object {
        const val NUMBER_TAG = "PHONE_NUMBER"
    }

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendCode.isEnabled = false
        val phonePattern = "^09\\d{9}$".toRegex()

        binding.btnSendCode.setOnClickListener {
            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            val intent = Intent(this, VerifyCodeActivity::class.java).apply {
                putExtra(NUMBER_TAG, phoneNumber)
            }
            startActivity(intent)
        }

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

            val isValidPhoneNumber =
                phonePattern.matches(binding.editTextPhoneNumber.text.toString())
            binding.btnSendCode.isEnabled = isChecked && isValidPhoneNumber
        }


        val privacyText = binding.textViewPrivacy.text
        val spannableString = SpannableString(privacyText)

        val clickableSpan = object : ClickableSpan() {
            override fun onClick(p0: View) {
                val intent = Intent(this@LoginActivity, PrivacyActivity::class.java)
                startActivity(intent)
            }
        }

        val startIndex = privacyText.indexOf("قوانین و مقررات")
        val endIndex = startIndex + "قوانین و مقررات".length
        spannableString.setSpan(clickableSpan, startIndex, endIndex, 0)

        binding.textViewPrivacy.text = spannableString

        binding.textViewPrivacy.movementMethod = LinkMovementMethod.getInstance()
    }
}