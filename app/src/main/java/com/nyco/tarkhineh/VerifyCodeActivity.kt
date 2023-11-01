package com.nyco.tarkhineh

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.graphics.fonts.FontFamily
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.res.ResourcesCompat
import com.nyco.tarkhineh.databinding.ActivityVerifyCodeBinding

class VerifyCodeActivity : AppCompatActivity() {

    val totalTimeMillis = 5000 // 2 minutes
    val countDownInterval = 1000 // 1 second
    private var countDownTimer: CountDownTimer? = null


    private lateinit var binding: ActivityVerifyCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        intent.let {
            val phoneNumber = it.getStringExtra(LoginActivity.NUMBER_TAG)
            binding.txtPhoneNumber.text = getString(R.string.verify_code_desc,phoneNumber)
        }

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


        val focusChangeListener = View.OnFocusChangeListener { view, hasFocus ->
            if (hasFocus) {
                view.setBackgroundResource(R.drawable.edit_text_border_green)
            } else {
                view.setBackgroundResource(R.drawable.edit_text_border)
            }
        }

        binding.editText1.onFocusChangeListener = focusChangeListener
        binding.editText2.onFocusChangeListener = focusChangeListener
        binding.editText3.onFocusChangeListener = focusChangeListener
        binding.editText4.onFocusChangeListener = focusChangeListener
        binding.editText5.onFocusChangeListener = focusChangeListener

        //this section may create problem in future so keep that in mind that if
        //something happened like memory loose
        binding.btnSendCode.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        }

        binding.txtEditNumber.setOnClickListener {
            finish()
        }

        startCountdownTimer()
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

    private fun convertToPersianDigits(input: String): String {
        val persianDigits = arrayOf("۰", "۱", "۲", "۳", "۴", "۵", "۶", "۷", "۸", "۹")
        val latinDigits = arrayOf("0", "1", "2", "3", "4", "5", "6", "7", "8", "9")

        var result = input
        for (i in 0..9) {
            result = result.replace(latinDigits[i], persianDigits[i])
        }

        return result
    }

    private fun startCountdownTimer() {

        val totalTimeMillis = 120000 // 2 minutes
        val countDownInterval = 1000 // 1 second

        val farsiTypeFace = ResourcesCompat.getFont(this,R.font.estedad_light)
        countDownTimer = object : CountDownTimer(totalTimeMillis.toLong(), countDownInterval.toLong()) {
            override fun onTick(millisUntilFinished: Long) {
                    val second = (millisUntilFinished / 1000) % 60
                    val minutes = (millisUntilFinished / 1000) / 60
                    val timeFormatted = String.format("%d:%02d", minutes, second)
                    // Convert Latin digits to Farsi digits
                    val farsiTimeFormatted = convertToPersianDigits(timeFormatted)

                    binding.numberCountDown.typeface = farsiTypeFace
                    binding.numberCountDown.text = farsiTimeFormatted

            }

            override fun onFinish() {
                val secondText = "دریافت مجدد کد"
                val spannableString = SpannableString(secondText)

                val clickableSpan = object : ClickableSpan() {
                    override fun onClick(p0: View) {
                        binding.txtCountDown.text = getString(R.string.countDown)
                        startCountdownTimer()
                    }
                }

                val startIndex = secondText.indexOf("دریافت مجدد کد")
                val endIndex = startIndex + "دریافت مجدد کد".length
                spannableString.setSpan(clickableSpan, startIndex, endIndex, 0)

                binding.txtCountDown.text = spannableString
                binding.txtCountDown.movementMethod = LinkMovementMethod.getInstance()
            }
        }
        countDownTimer?.start()
    }
}