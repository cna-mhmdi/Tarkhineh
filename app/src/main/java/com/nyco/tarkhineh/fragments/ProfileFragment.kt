package com.nyco.tarkhineh.fragments

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.nyco.tarkhineh.TarkhinehApplication
import com.nyco.tarkhineh.TarkhinehViewModel
import com.nyco.tarkhineh.databinding.FragmentProfileBinding
import com.nyco.tarkhineh.ktx.FaqsActivity
import com.nyco.tarkhineh.ktx.PrivacyActivity
import com.nyco.tarkhineh.ktx.UserInfoActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater, container, false)

        val tarkhinehRepository = (activity?.application as TarkhinehApplication).tarkhinehRepository
        val tarkhinehViewModel = ViewModelProvider(this,object :ViewModelProvider.Factory{
            override fun <T : ViewModel> create(modelClass: Class<T>): T {
                return TarkhinehViewModel(tarkhinehRepository)as T
            }
        })[TarkhinehViewModel::class.java]

        val sharedPreferences = activity?.getSharedPreferences("TOKENS", Context.MODE_PRIVATE)
        val accessToken = sharedPreferences?.getString("access_token", null)
        Toast.makeText(requireContext(),accessToken,Toast.LENGTH_SHORT).show()
        val completeToken = if (accessToken != null) "Bearer $accessToken" else null

        tarkhinehViewModel.getUsersDetail(completeToken!!)

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

        }

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}