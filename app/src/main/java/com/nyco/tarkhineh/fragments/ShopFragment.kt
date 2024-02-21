package com.nyco.tarkhineh.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.R.color.Primary
import com.nyco.tarkhineh.adapters.ShoppingCardAdapter
import com.nyco.tarkhineh.databinding.FragmentShopBinding
import com.nyco.tarkhineh.ktx.PaymentResActivity
import com.nyco.tarkhineh.model.Shopping

class ShopFragment : Fragment() {

    private var _binding: FragmentShopBinding? = null
    private val binding get() = _binding!!

    private lateinit var shoppingCardRecyclerView: RecyclerView
    private val shopAdapter by lazy {
        ShoppingCardAdapter()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentShopBinding.inflate(inflater,container,false)
        (activity as AppCompatActivity).setSupportActionBar(binding.shopToolbar)
        (activity as AppCompatActivity).supportActionBar?.title = null

        val items = listOf(
            Shopping("غذای 1 ","قیمت 1"),
            Shopping("غذای 2 ","قیمت 2"),
            Shopping("غذای 3 ","قیمت 3"),
            Shopping("غذای 4 ","قیمت 4"),
            Shopping("غذای 5 ","قیمت 5")
        )
        shoppingCardRecyclerView = binding.recyclerShopingCard
        shoppingCardRecyclerView.adapter = shopAdapter
        shopAdapter.addItems(items)

        binding.btnPayment.setOnClickListener {

            binding.infoTxt.setTextColor(resources.getColor(Primary))
            binding.infoImg.setColorFilter(resources.getColor(Primary))
            binding.infoNote.setTextColor(resources.getColor(Primary))
            binding.layoutShopingCard.visibility = View.GONE
            binding.layoutCOI.visibility = View.VISIBLE
        }

        binding.radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radio_peyk -> {
                    binding.cardAdresses.visibility = View.VISIBLE
                    binding.cardShobe.visibility = View.GONE
                }
                R.id.radio_hozoori -> {
                    binding.cardAdresses.visibility = View.GONE
                    binding.cardShobe.visibility = View.VISIBLE
                }
            }
        }

        binding.btnCOI.setOnClickListener {
            binding.paymentImg.setColorFilter(resources.getColor(Primary))
            binding.paymentNote.setTextColor(resources.getColor(Primary))
            binding.peymanetTxt.setTextColor(resources.getColor(Primary))

            binding.layoutCOI.visibility = View.GONE
            binding.layoutPayment.visibility = View.VISIBLE
        }

        binding.radioPayment.setOnCheckedChangeListener { _, checkedId ->
            when(checkedId){
                R.id.radio_btn_hozoori -> {
                    binding.cardPaymentHozoori.visibility = View.VISIBLE
                    binding.cardPaymentInternet.visibility = View.GONE
                }
                R.id.radio_btn_internet -> {
                    binding.cardPaymentInternet.visibility = View.VISIBLE
                    binding.cardPaymentHozoori.visibility = View.GONE
                }
            }
        }

        binding.btnFinalPay.setOnClickListener {
            startActivity(Intent(requireContext(),PaymentResActivity::class.java))
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}