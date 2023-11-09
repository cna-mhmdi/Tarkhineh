package com.nyco.tarkhineh

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.CountDownTimer
import android.text.Editable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.content.res.ResourcesCompat
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.databinding.ActivityVerifyCodeBinding
import com.nyco.tarkhineh.model.LoginReq
import com.nyco.tarkhineh.model.OTPRequest
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class VerifyCodeActivity : AppCompatActivity() {

    private var countDownTimer: CountDownTimer? = null
    private lateinit var tarkhinehViewModel: TarkhinehViewModel

    private lateinit var binding: ActivityVerifyCodeBinding
    private lateinit var otpCode: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVerifyCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startCountdownTimer()

        setupBackspaceListener(binding.editText2, binding.editText1)
        setupBackspaceListener(binding.editText3, binding.editText2)
        setupBackspaceListener(binding.editText4, binding.editText3)
        setupBackspaceListener(binding.editText5, binding.editText4)

        val tarkhinehRepository = (application as TarkhinehApplication).tarkhinehRepository
        tarkhinehViewModel = ViewModelProvider(this, object : ViewModelProvider.Factory {
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TarkhinehViewModel(tarkhinehRepository) as T
            }
        })[TarkhinehViewModel::class.java]

        intent.let {
            val phoneNumber = it.getStringExtra(LoginActivity.NUMBER_TAG)
            binding.txtPhoneNumber.text = getString(R.string.verify_code_desc, phoneNumber)
        }

        tarkhinehViewModel.otp.observe(this) { otpResponse ->
            otpCode = otpResponse.code
            Log.d("THISISMESSAGEFOR",otpCode)
        }

        binding.btnSendCode.setOnClickListener {

            val userCode = binding.editText1.text.toString() +
                    binding.editText2.text.toString() +
                    binding.editText3.text.toString() +
                    binding.editText4.text.toString() +
                    binding.editText5.text.toString()
            intent.let {
                val phoneNumber = it.getStringExtra(LoginActivity.NUMBER_TAG).toString()
                val login = LoginReq(phoneNumber,userCode)
                tarkhinehViewModel.sendLogin(login)

            }

            tarkhinehViewModel.otp.observe(this){otpResponse->
                val otpCode = otpResponse.code
                Log.d("THISISMESSAGEFOR",otpCode)
            }

            tarkhinehViewModel.login.observe(this){loginResponse->
                val message = loginResponse.message
                val accessToken = loginResponse.access_token
                val refreshToken = loginResponse.refresh_token
                Toast.makeText(this,message,Toast.LENGTH_LONG).show()
            }


//            if (userCode == otpCode) {
//
//                Toast.makeText(this, "به ترخینه خوش آمدید", Toast.LENGTH_LONG).show()
//                val intent = Intent(this, MainActivity::class.java)
//                intent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
//                startActivity(intent)
//                finish()
//
//            } else {
//
//                val editTexts = listOf(
//                    binding.editText1,
//                    binding.editText2,
//                    binding.editText3,
//                    binding.editText4,
//                    binding.editText5
//                )
//
//                CoroutineScope(Dispatchers.Main).launch {
//                    editTexts.forEach { editText ->
//                        editText.background = ContextCompat.getDrawable(
//                            this@VerifyCodeActivity,
//                            R.drawable.edit_text_border_red
//                        )
//                    }
//
//                    delay(1000)
//
//                    editTexts.forEach { editText ->
//                        editText.background = ContextCompat.getDrawable(
//                            this@VerifyCodeActivity,
//                            R.drawable.edit_text_border
//                        )
//                    }
//                }
//            }
        }

        binding.txtEditNumber.setOnClickListener {
            finish()
        }

        /*
        This part is a text watcher that checks that if a one-digit number
         is entered in the edit text, it moves the focus to the next edit text.
         */
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
                        val imm =
                            getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
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


        /*
        This section changes the background of each edit text when the focus is on it
         */
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

    }

    override fun onBackPressed() {

    }

    /*
    This function is for focusing when you backspace
     in the edit text and it moves the focus to the previous text edit text.
     */
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

    /*
    This function is a countdown, which after reaching the end of a text
     turns into a link, by clicking on it, this countdown starts again.
     */
    private fun startCountdownTimer() {

        val totalTimeMillis = 121000
        val countDownInterval = 1000

        val farsiTypeFace = ResourcesCompat.getFont(this, R.font.estedad_light)
        countDownTimer =
            object : CountDownTimer(totalTimeMillis.toLong(), countDownInterval.toLong()) {
                override fun onTick(millisUntilFinished: Long) {
                    val seconds = ((millisUntilFinished - 1000) / 1000) % 60
                    val minutes = ((millisUntilFinished - 1000) / 1000) / 60
                    val timeFormatted = String.format("%d:%02d", minutes, seconds)

                    val farsiTimeFormatted = convertToPersianDigits(timeFormatted)

                    binding.numberCountDown.typeface = farsiTypeFace
                    binding.numberCountDown.text = farsiTimeFormatted

                }

                override fun onFinish() {
                    val secondText = "دریافت مجدد کد"
                    val spannableString = SpannableString(secondText)

                    val clickableSpan = object : ClickableSpan() {
                        override fun onClick(p0: View) {

                            intent.let {
                                val phoneNumber = it.getStringExtra(LoginActivity.NUMBER_TAG)
                                val otpRequest = OTPRequest(phoneNumber)
                                tarkhinehViewModel.sendOTPCode(otpRequest)
                            }

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