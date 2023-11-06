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
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.databinding.ActivityLoginBinding
import com.nyco.tarkhineh.model.OTPRequest

class LoginActivity : AppCompatActivity() {

    companion object {
        const val NUMBER_TAG = "PHONE_NUMBER"
    }

    private lateinit var binding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val tarkhinehRepository = (application as TarkhinehApplication).tarkhinehRepository
        val tarkhinehViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TarkhinehViewModel(tarkhinehRepository) as T
            }
        })[TarkhinehViewModel::class.java]

        binding.btnSendCode.isEnabled = false

        binding.btnSendCode.setOnClickListener {
            val phoneNumber = binding.editTextPhoneNumber.text.toString()
            val phone = OTPRequest(phoneNumber)
            tarkhinehViewModel.sendOTPCode(phone, this)

            val intent = Intent(this, VerifyCodeActivity::class.java).apply {
                putExtra(NUMBER_TAG, phoneNumber)
            }
            startActivity(intent)
        }


        /*
        This part checks whether the number is 11 digits and starts with 09 or not.
         Next to it, it checks if the checkbox is checked
         */
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

            val isValidPhoneNumber =
                phonePattern.matches(binding.editTextPhoneNumber.text.toString())
            binding.btnSendCode.isEnabled = isChecked && isValidPhoneNumber
        }
//
//        /*
//        This section is used to convert a part of the text view
//         into a link to go to the rules and regulations page by clicking on it
//         */
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

