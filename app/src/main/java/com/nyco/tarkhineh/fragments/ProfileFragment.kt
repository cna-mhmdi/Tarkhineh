package com.nyco.tarkhineh.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.TarkhinehApplication
import com.nyco.tarkhineh.TarkhinehViewModel
import com.nyco.tarkhineh.databinding.FragmentProfileBinding
import com.nyco.tarkhineh.databinding.LayoutDialogLogoutBinding
import com.nyco.tarkhineh.ktx.FaqsActivity
import com.nyco.tarkhineh.ktx.LoginActivity
import com.nyco.tarkhineh.ktx.PrivacyActivity
import com.nyco.tarkhineh.ktx.UserInfoActivity
import com.nyco.tarkhineh.model.SaveDataResponse
import com.nyco.tarkhineh.model.UpdateUser
import com.nyco.tarkhineh.model.UserProfile

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    private var _dialogBinding: LayoutDialogLogoutBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)
        _dialogBinding = LayoutDialogLogoutBinding.inflate(inflater,null,false)

        val sharedPreferences = requireContext().getSharedPreferences("NICKNAME", Context.MODE_PRIVATE)
        val nickName = sharedPreferences?.getString("NickName", null)

        binding.userName.text = nickName ?: "کاربر ترخینه"

        binding.userInformation.setOnClickListener {
            startActivity(Intent(requireContext(), UserInfoActivity::class.java))
        }
        binding.userFavorite.setOnClickListener {

        }
        binding.userLocations.setOnClickListener {

        }
        binding.appFaqs.setOnClickListener {
            startActivity(Intent(requireContext(), FaqsActivity::class.java))
        }
        binding.appPrivacy.setOnClickListener {
            startActivity(Intent(requireContext(), PrivacyActivity::class.java))
        }
        binding.appCalling.setOnClickListener {

        }
        binding.appLogout.setOnClickListener {
            showLogoutDialog()
        }

        return binding.root
    }

    private fun showLogoutDialog() {
        val dialogView = LayoutInflater.from(requireContext()).inflate(R.layout.layout_dialog_logout,null)
        val dialogBinding = LayoutDialogLogoutBinding.bind(dialogView)
        val dialog = AlertDialog.Builder(requireContext())
            .setView(dialogView)
            .setCancelable(false)
            .create()

        dialogBinding.iconClose.setOnClickListener {
            dialog.dismiss()
        }

        dialogBinding.btnLogout.setOnClickListener {
            startActivity(Intent(requireContext(),LoginActivity::class.java).also {
                it.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            })
            dialog.dismiss()
        }

        dialogBinding.btnBack.setOnClickListener {
            dialog.dismiss()
        }

        dialog.show()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
        _dialogBinding = null
    }
}