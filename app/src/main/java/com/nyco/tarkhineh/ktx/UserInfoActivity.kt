package com.nyco.tarkhineh.ktx

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.util.Log
import android.util.Patterns
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.TarkhinehApplication
import com.nyco.tarkhineh.TarkhinehViewModel
import com.nyco.tarkhineh.databinding.ActivityUserInfoBinding
import com.nyco.tarkhineh.model.UpdateUser
import com.nyco.tarkhineh.model.UserProfile

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

        val sharedPreferences = this.getSharedPreferences("TOKENS", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences?.getString("access_token", null)
        val completeToken = if (accessToken != null) "Bearer $accessToken" else null

        tarkhinehViewModel.getUsersDetail(completeToken!!)

        tarkhinehViewModel.users.observe(this){ userProfile ->
            binding.editTextFirstName.text = Editable.Factory.getInstance().newEditable(userProfile.first_name ?: "")
            binding.editTextLastName.text = Editable.Factory.getInstance().newEditable(userProfile.last_name ?: "")
            binding.editTextEmail.text = Editable.Factory.getInstance().newEditable(userProfile.email ?: "")
            binding.editTextPhoneNumber.text = Editable.Factory.getInstance().newEditable(userProfile.phone_number ?: "")
            binding.editTextBirthDay.text = Editable.Factory.getInstance().newEditable(userProfile.date_birth ?: "")
            binding.editTextDisplayName.text = Editable.Factory.getInstance().newEditable(userProfile.nick_name ?: "")
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
                val email = editTexts[2].text.toString().trim()
                if (email.isEmpty() || !Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                    editTexts[2].error = "ایمیل معتبر وارد کنید!"
                    isButtonEnable = true
                }else{
                    val phonePattern = "^09\\d{9}$".toRegex()
                    if (!phonePattern.matches(editTexts[3].text.toString().trim())){
                        editTexts[3].error = "شماره همراه معتبر وارد کنید!"
                        isButtonEnable = true
                    } else{
                        binding.editInfo.text = "ویرایش اطلاعات"
                        editTexts.forEach {
                            it.isEnabled = false
                            it.isFocusableInTouchMode = false
                        }

                        val updateUser = UpdateUser(
                            editTexts[0].text.toString(),
                            editTexts[1].text.toString(),
                            editTexts[2].text.toString(),
                            editTexts[4].text.toString(),
                            editTexts[5].text.toString(),
                        )

                        tarkhinehViewModel.updateUserDetail(completeToken,updateUser)

                        tarkhinehViewModel.updateUser.observe(this){ updateUser->
                            Toast.makeText(this, "تغییرات اعمال شد", Toast.LENGTH_SHORT).show()

                            val sharedPreferencesNickname = this.getSharedPreferences("NICKNAME", Context.MODE_PRIVATE)
                            val editor = sharedPreferencesNickname.edit()
                            editor.putString("NickName", binding.editTextDisplayName.text.toString())
                            editor.apply()
                        }

                        tarkhinehViewModel.getUpdateUserError().observe(this){ error->
                            Toast.makeText(this, error, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        }
    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}