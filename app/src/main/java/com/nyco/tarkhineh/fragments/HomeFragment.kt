package com.nyco.tarkhineh.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.SpecialOfferAdapter
import com.nyco.tarkhineh.databinding.FragmentHomeBinding
import com.nyco.tarkhineh.ktx.PrivacyActivity
import com.nyco.tarkhineh.model.FoodOffers

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerSpecialOffer: RecyclerView

    private val specialOfferAdapter by lazy {
        SpecialOfferAdapter(object : SpecialOfferAdapter.SpecialOfferClickListener{
            override fun onOfferClick(offers: FoodOffers) {
                openSpecialOffer(offers)
            }
        }, requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        (activity as AppCompatActivity).setSupportActionBar(binding.mainToolbar)
        (activity as AppCompatActivity).supportActionBar?.title = null

        val imageList = ArrayList<SlideModel>()
        imageList.add(SlideModel(R.drawable.slider1))
        imageList.add(SlideModel(R.drawable.slider2))
        imageList.add(SlideModel(R.drawable.slider3))
        imageList.add(SlideModel(R.drawable.slider4))
        imageList.add(SlideModel(R.drawable.slider5))
        imageList.add(SlideModel(R.drawable.slider6))
        binding.mainImageSlider.setImageList(imageList, ScaleTypes.CENTER_INSIDE)
        binding.layoutMainMenu.layoutMainCourse.setOnClickListener {
//            startActivity(Intent(requireContext(),PrivacyActivity::class.java))
        }
        binding.layoutMainMenu.layoutAppetizer.setOnClickListener {  }
        binding.layoutMainMenu.layoutDessert.setOnClickListener {  }
        binding.layoutMainMenu.layoutDessert.setOnClickListener {  }

        val foodOffersList = listOf(
            FoodOffers("Special Pizza", "20%", "$10.99", "4.5"),
            FoodOffers("Burger Combo", "15%", "$8.99", "4.0"),
            FoodOffers("Pasta Delight", "25%", "$12.99", "4.7"),
            FoodOffers("Sushi Feast", "10%", "$15.99", "4.2"),
            FoodOffers("Dessert Delicacy", "30%", "$5.99", "4.8")
        )

        recyclerSpecialOffer = binding.layoutSpecialOffer.recyclerSpecialOffer
        recyclerSpecialOffer.adapter = specialOfferAdapter

        specialOfferAdapter.addOffers(foodOffersList)


        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun openSpecialOffer(offers: FoodOffers){
        Toast.makeText(requireContext(),offers.foodName,Toast.LENGTH_SHORT).show()
    }

}