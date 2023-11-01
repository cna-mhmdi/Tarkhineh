package com.nyco.tarkhineh

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.KeyEvent
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.nyco.tarkhineh.databinding.ActivityVerifyCodeBinding

class VerifyCodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityVerifyCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val textWatcher = object : TextWatcher {
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun afterTextChanged(p0: Editable?) {
                when {
                    p0 === binding.editText1.text && p0?.length == 1 -> binding.editText2.requestFocus()
                    p0 === binding.editText2.text && p0?.length == 1 -> binding.editText3.requestFocus()
                    p0 === binding.editText3.text && p0?.length == 1 -> binding.editText4.requestFocus()
                    p0 === binding.editText4.text && p0?.length == 1 -> binding.editText5.requestFocus()
                    p0 === binding.editText5.text && p0?.length == 1 -> {
                        val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                        imm.hideSoftInputFromWindow(binding.editText5.windowToken, 0)
                    }
                }
            }
        }

        binding.editText1.addTextChangedListener(textWatcher)
        binding.editText2.addTextChangedListener(textWatcher)
        binding.editText3.addTextChangedListener(textWatcher)
        binding.editText4.addTextChangedListener(textWatcher)
        binding.editText5.addTextChangedListener(textWatcher)

        setupBackspaceListener(binding.editText2, binding.editText1)
        setupBackspaceListener(binding.editText3, binding.editText2)
        setupBackspaceListener(binding.editText4, binding.editText3)
        setupBackspaceListener(binding.editText5, binding.editText4)
    }

    @Deprecated("Deprecated in Java")
    override fun onBackPressed() {

    }

    private fun setupBackspaceListener(editText: EditText, previousEditText: EditText) {
        editText.setOnKeyListener { _, keyCode, event ->
            if (event.action == KeyEvent.ACTION_DOWN && keyCode == KeyEvent.KEYCODE_DEL && editText.text.isEmpty()) {
                previousEditText.requestFocus()
                return@setOnKeyListener true
            }
            false
        }
    }
}