package com.nyco.tarkhineh.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.databinding.FragmentProfileBinding
import com.nyco.tarkhineh.ktx.FaqsActivity
import com.nyco.tarkhineh.ktx.PrivacyActivity

class ProfileFragment : Fragment() {

    private var _binding: FragmentProfileBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentProfileBinding.inflate(inflater,container,false)

        binding.userInformation.setOnClickListener {

        }
        binding.userFavorite.setOnClickListener {

        }
        binding.userLocations.setOnClickListener {

        }
        binding.appFaqs.setOnClickListener {
            startActivity(Intent(requireContext(),FaqsActivity::class.java))
        }
        binding.appPrivacy.setOnClickListener {
            startActivity(Intent(requireContext(),PrivacyActivity::class.java))
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