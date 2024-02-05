package com.nyco.tarkhineh.fragments

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
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
import com.nyco.tarkhineh.ktx.DetailFoodActivity
import com.nyco.tarkhineh.ktx.MenuActivity
import com.nyco.tarkhineh.model.MainFood

class HomeFragment : Fragment() {


    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private lateinit var recyclerSpecialOffer: RecyclerView
    private lateinit var recyclerPopFoods: RecyclerView
    private lateinit var recyclerNonPersianFoods: RecyclerView

    private lateinit var mainFood: MainFood
    private lateinit var menuFood: MainFood

    private val mainAdapter by lazy {
        MainAdapter(object : MainAdapter.MainFoodsClickListener {
            override fun onMainFoodsClick(mainFood: MainFood) {
                openMainFood(mainFood)
            }
        }, requireContext())
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
        binding.layoutMainMenu.layoutMainCourse.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    MenuActivity::class.java
                )
            )
        }
        binding.layoutMainMenu.layoutAppetizer.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    MenuActivity::class.java
                )
            )
        }
        binding.layoutMainMenu.layoutDessert.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    MenuActivity::class.java
                )
            )
        }
        binding.layoutMainMenu.layoutDessert.setOnClickListener {
            startActivity(
                Intent(
                    requireContext(),
                    MenuActivity::class.java
                )
            )
        }

        val foodList = listOf(
            MainFood("پیتزا ویژه", "۲۰%", "۱۲.۲۰ تومان", "۴.۵","این توضیحات غذای پیتزای ویژه ات اصلن بهبه عالیه بیایین بخرین"),
            MainFood("کمبو برگر", "۲۰%", "۱۲.۲۰ تومان", "۴.۵","این توضیحات غذای کمبو برگر است اصلن بهبه عالیه بیایین بخرین"),
            MainFood("لذت پاستا", "۲۰%", "۱۲.۲۰۰ تومان", "۴.۵","این توضیحات غذای لذت پاستا است اصلن بهبه عالیه بیایین بخرین"),
            MainFood("جشنواره سوشی", "۲۰%", "۱۲.۲۰۲.۲۰ تومان", "۴.۵","این توضیحات جشنواره سوشی اصلن بهبه عالیه بیایین بخرین"),
            MainFood("خوشمزه دسر", "۲۰%", "۱۲.۲۰.۲۰ تومان", "۴.۵","این توضیحات خوشمزه دسر ات اصلن بهبه عالیه بیایین بخرین")
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

    private fun openMainFood(mainFood: MainFood) {
        val intent = Intent(requireContext(),DetailFoodActivity::class.java).apply {
            putExtra(DetailFoodActivity.MOVIE_ID,mainFood)
        }
        startActivity(intent)
    }

//    private fun updateFavoriteButtonState(){
//
//        val tint = if (mainFood.isFavorite) Color.RED
//        binding.layoutSpecialOffer.recyclerSpecialOffer. = ColorStateList.valueOf(tint)
//
//    }
//
//    private fun toggleFavoriteState(){
//
//    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}