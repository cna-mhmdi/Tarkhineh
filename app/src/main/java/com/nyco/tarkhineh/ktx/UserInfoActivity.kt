package com.nyco.tarkhineh.ktx

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    private var isButtonEnable: Boolean = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTexts = listOf (
            binding.editTextFirstName,
            binding.editTextLastName,
            binding.editTextEmail,
            binding.editTextPhoneNumber,
            binding.editTextBirthDay,
            binding.editTextDisplayName
        )

        setSupportActionBar(binding.userInfoToolbar)
        supportActionBar?.title = null
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.userInfoToolbar.setNavigationIcon(R.drawable.arrow_left1)

        editTexts.forEach {
            it.isEnabled = false
            it.isFocusableInTouchMode = false
        }

        binding.editInfo.setOnClickListener {
            isButtonEnable = !isButtonEnable
            if (isButtonEnable){
                binding.editInfo.text = "اعمال تغییرات"
                editTexts.forEach {
                    it.isEnabled = true
                    it.isFocusableInTouchMode = true
                }
            }else{
                binding.editInfo.text = "ویرایش اطلاعات"
                editTexts.forEach {
                    it.isEnabled = false
                    it.isFocusableInTouchMode = false
                }
                Toast.makeText(this,"تغییرات اعمال شد",Toast.LENGTH_SHORT).show()
            }
        }
    }
}