package com.nyco.tarkhineh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.denzcoskun.imageslider.constants.ScaleTypes
import com.denzcoskun.imageslider.models.SlideModel
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.SpecialOfferAdapter
import com.nyco.tarkhineh.databinding.FragmentHomeBinding
import com.nyco.tarkhineh.model.FoodOffers

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerSpecialOffer: RecyclerView

    private val specialOfferAdapter by lazy {
        SpecialOfferAdapter(object : SpecialOfferAdapter.SpecialOfferClickListener {
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
        binding.layoutMainMenu.layoutAppetizer.setOnClickListener { }
        binding.layoutMainMenu.layoutDessert.setOnClickListener { }
        binding.layoutMainMenu.layoutDessert.setOnClickListener { }

        val foodOffersList = listOf(
            FoodOffers("پیتزا ویژه", "۲۰%", "۱۲.۲۰ تومان", "۴.۵"),
            FoodOffers("کمبو برگر", "۲۰%", "۱۲.۲۰ تومان", "۴.۵"),
            FoodOffers("لذت پاستا", "۲۰%", "۱۲.۲۰۰ تومان", "۴.۵"),
            FoodOffers("جشنواره سوشی", "۲۰%", "۱۲.۲۰۲.۲۰ تومان", "۴.۵"),
            FoodOffers("خوشمزه دسر", "۲۰%", "۱۲.۲۰.۲۰ تومان", "۴.۵")
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

    private fun openSpecialOffer(offers: FoodOffers) {
        Toast.makeText(requireContext(), offers.foodName, Toast.LENGTH_SHORT).show()
    }

}