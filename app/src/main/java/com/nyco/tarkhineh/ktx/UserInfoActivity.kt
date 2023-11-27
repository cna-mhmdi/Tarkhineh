package com.nyco.tarkhineh.ktx

import android.annotation.SuppressLint
import android.os.Bundle
import android.text.Editable
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.TarkhinehApplication
import com.nyco.tarkhineh.TarkhinehViewModel
import com.nyco.tarkhineh.databinding.ActivityUserInfoBinding

class UserInfoActivity : AppCompatActivity() {

    private lateinit var binding: ActivityUserInfoBinding

    private var isButtonEnable: Boolean = false

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserInfoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editTexts = listOf(
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

        val tarkhinehRepository = (application as TarkhinehApplication).tarkhinehRepository
        val tarkhinehViewModel = ViewModelProvider(this,object : ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TarkhinehViewModel(tarkhinehRepository) as T
            }
        })[TarkhinehViewModel::class.java]

        tarkhinehViewModel.users.observe(this){ userProfile ->
            binding.editTextFirstName.text = Editable.Factory.getInstance().newEditable(userProfile.first_name ?: "")
            binding.editTextLastName.text = Editable.Factory.getInstance().newEditable(userProfile.last_name ?: "")
            binding.editTextEmail.text = Editable.Factory.getInstance().newEditable(userProfile.email ?: "")
            binding.editTextPhoneNumber.text = Editable.Factory.getInstance().newEditable(userProfile.phone_number ?: "")
            binding.editTextBirthDay.text = Editable.Factory.getInstance().newEditable(userProfile.date_birth ?: "")
            binding.editTextDisplayName.text = Editable.Factory.getInstance().newEditable(userProfile.nick_name ?: "")
        }

        tarkhinehViewModel.getUsersError().observe(this){error->
            Toast.makeText(this,error,Toast.LENGTH_LONG).show()
        }

        binding.editInfo.setOnClickListener {
            isButtonEnable = !isButtonEnable
            if (isButtonEnable) {
                binding.editInfo.text = "اعمال تغییرات"
                editTexts.forEach {
                    it.isEnabled = true
                    it.isFocusableInTouchMode = true
                }
            } else {
                binding.editInfo.text = "ویرایش اطلاعات"
                editTexts.forEach {
                    it.isEnabled = false
                    it.isFocusableInTouchMode = false
                }
                Toast.makeText(this, "تغییرات اعمال شد", Toast.LENGTH_SHORT).show()
            }
        }
    }
}