package com.nyco.tarkhineh.fragments

import android.content.Intent
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
import com.nyco.tarkhineh.adapters.MainAdapter
import com.nyco.tarkhineh.databinding.FragmentHomeBinding
import com.nyco.tarkhineh.ktx.MenuActivity
import com.nyco.tarkhineh.model.MainFood

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerSpecialOffer: RecyclerView
    private lateinit var recyclerPopFoods: RecyclerView
    private lateinit var recyclerNonPersianFoods: RecyclerView

    private val mainAdapter by lazy {
        MainAdapter(object : MainAdapter.MainFoodsClickListener{
            override fun onMainFoodsClick(mainFood: MainFood) {
                Toast.makeText(requireContext(), mainFood.foodName, Toast.LENGTH_SHORT).show()
            }
        },requireContext())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

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
        binding.layoutMainMenu.layoutMainCourse.setOnClickListener { startActivity(Intent(requireContext(),MenuActivity::class.java))}
        binding.layoutMainMenu.layoutAppetizer.setOnClickListener { startActivity(Intent(requireContext(),MenuActivity::class.java))}
        binding.layoutMainMenu.layoutDessert.setOnClickListener { startActivity(Intent(requireContext(),MenuActivity::class.java))}
        binding.layoutMainMenu.layoutDessert.setOnClickListener { startActivity(Intent(requireContext(),MenuActivity::class.java))}

        val foodList = listOf(
            MainFood("پیتزا ویژه", "۲۰%", "۱۲.۲۰ تومان", "۴.۵"),
            MainFood("کمبو برگر", "۲۰%", "۱۲.۲۰ تومان", "۴.۵"),
            MainFood("لذت پاستا", "۲۰%", "۱۲.۲۰۰ تومان", "۴.۵"),
            MainFood("جشنواره سوشی", "۲۰%", "۱۲.۲۰۲.۲۰ تومان", "۴.۵"),
            MainFood("خوشمزه دسر", "۲۰%", "۱۲.۲۰.۲۰ تومان", "۴.۵")
        )

        recyclerSpecialOffer = binding.layoutSpecialOffer.recyclerSpecialOffer
        recyclerSpecialOffer.adapter = mainAdapter
        mainAdapter.addMainFood(foodList)

        recyclerPopFoods = binding.layoutPopularFood.recyclerPopularFood
        recyclerPopFoods.adapter = mainAdapter
        mainAdapter.addMainFood(foodList)

        recyclerNonPersianFoods = binding.layoutNonPersianFood.recyclerNonPersianFood
        recyclerNonPersianFoods.adapter = mainAdapter
        mainAdapter.addMainFood(foodList)

        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}