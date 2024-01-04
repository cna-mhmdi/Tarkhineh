package com.nyco.tarkhineh.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.MenuAdapter
import com.nyco.tarkhineh.databinding.FragmentMainCourseBinding
import com.nyco.tarkhineh.model.MenuFood

class MainCourseFragment : Fragment() {

    private lateinit var recyclerView1: RecyclerView
    private lateinit var recyclerView2: RecyclerView
    private lateinit var recyclerView3: RecyclerView
    private lateinit var recyclerView4: RecyclerView

    private val menuAdapter1 by lazy {
        MenuAdapter(object : MenuAdapter.MenuFoodsClickListener {
            override fun onMenuFoodsClick(menuFood: MenuFood) {
                Toast.makeText(requireContext(), menuFood.foodName, Toast.LENGTH_SHORT).show()
            }
        }, requireContext())
    }

    private val menuAdapter2 by lazy {
        MenuAdapter(object : MenuAdapter.MenuFoodsClickListener {
            override fun onMenuFoodsClick(menuFood: MenuFood) {
                Toast.makeText(requireContext(), menuFood.foodName, Toast.LENGTH_SHORT).show()
            }
        }, requireContext())
    }

    private val menuAdapter3 by lazy {
        MenuAdapter(object : MenuAdapter.MenuFoodsClickListener {
            override fun onMenuFoodsClick(menuFood: MenuFood) {
                Toast.makeText(requireContext(), menuFood.foodName, Toast.LENGTH_SHORT).show()
            }
        }, requireContext())
    }

    private val menuAdapter4 by lazy {
        MenuAdapter(object : MenuAdapter.MenuFoodsClickListener {
            override fun onMenuFoodsClick(menuFood: MenuFood) {
                Toast.makeText(requireContext(), menuFood.foodName, Toast.LENGTH_SHORT).show()
            }
        }, requireContext())
    }


    private var _binding: FragmentMainCourseBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMainCourseBinding.inflate(inflater, container, false)


        val menuList1 = listOf(
            MenuFood(
                "پیتزا",
                "پیتزای خوشمزه با انواع تاپینگ. ترکیبی ایده‌آل از پنیر، گوجه و تاپینگ‌های مورد علاقه شما.",
                "20%",
                "۱۲٫۹۹ دلار"
            ),
            MenuFood(
                "برگر",
                "برگر گوشتی آبدار با پنیر. همراه با سیب‌زمینی سرخ شده به همراه.",
                "15%",
                "۸٫۹۹ دلار"
            ),
            MenuFood(
                "پاستا",
                "پاستای ایتالیایی کلاسیک با سس گوجه. تزئین شده با پنیر پارمسان و ریحان تازه.",
                "10%",
                "۱۰٫۹۹ دلار"
            ),
            MenuFood(
                "سالاد",
                "سالاد تازه با سس سرکه‌وسس. یک انتخاب سالم و تازه.",
                "5%",
                "۶٫۹۹ دلار"
            )
        )

        recyclerView1 = binding.layoutMenuRec1.menuRecycler1
        recyclerView1.adapter = menuAdapter1
        menuAdapter1.addMenuFood(menuList1)

        val menuList2 = listOf(
            MenuFood(
                "سوشی",
                "غذاهای متنوع سوشی با وسابی. یک ذائقه‌ی ژاپنی با هر حرکت.",
                "25%",
                "۱۴٫۹۹ دلار"
            ),
            MenuFood(
                "استیک",
                "استیک سرخ شده با سیب‌زمینی پوره. به دقت پخته شده و با گیاهان خاص ادویه‌ای نمکین.",
                "18%",
                "۱۶٫۹۹ دلار"
            ),
            MenuFood(
                "سوپ",
                "سوپ خودساخته نودل مرغ. گرم و دلپذیر، همانند آنچه مادربزرگ می‌پزید.",
                "12%",
                "۷٫۹۹ دلار"
            ),
            MenuFood(
                "ساندویچ",
                "ساندویچ بلدرچین و آووکادو. تازه تهیه شده با بهترین مواد اولیه.",
                "8%",
                "۹٫۹۹ دلار"
            )
        )

        recyclerView2 = binding.layoutMenuRec2.menuRecycler2
        recyclerView2.adapter = menuAdapter2
        menuAdapter2.addMenuFood(menuList2)

        val menuList3 = listOf(
            MenuFood(
                "تاکوس",
                "تاکوهای مرغ تند با سالسا. یک شکلک از طعم‌ها در هر تاکوشل.",
                "15%",
                "۱۱٫۹۹ دلار"
            ),
            MenuFood(
                "میگو",
                "میگوهای سوخته با سس نمکی و سالاد برنج. میگوهای لذیذ به دقت پخته شده.",
                "22%",
                "۱۳٫۹۹ دلار"
            ),
            MenuFood(
                "کاسه کینوا",
                "کینوا سالم با سبزیجات. پر از مواد مغذی و خوبی‌ها.",
                "10%",
                "۹٫۹۹ دلار"
            ),
            MenuFood(
                "دسر",
                "کیک لاوا شکلات با بستنی وانیل. یک پایان شیرین برای وعده غذایی شما.",
                "5%",
                "۵٫۹۹ دلار"
            )
        )

        recyclerView3 = binding.layoutMenuRec3.menuRecycler3
        recyclerView3.adapter = menuAdapter3
        menuAdapter3.addMenuFood(menuList3)

        val menuList4 = listOf(
            MenuFood(
                "کاری",
                "کاری سبزی با برنج بسمتی. ترکیبی عالی از ادویه‌ها و سبزیجات.",
                "18%",
                "۱۲٫۹۹ دلار"
            ),
            MenuFood(
                "ماهی",
                "ماهی سرخ شده با لیمو و گیاهان دارویی. سبک و خوشمزه، مناسب برای عاشقان دریایی‌ماهی.",
                "20%",
                "۱۵٫۹۹ دلار"
            ),
            MenuFood("اسموتی", "اسموتی توت مختلف با ماست. تازه و مغذی.", "8%", "۴٫۹۹ دلار"),
            MenuFood(
                "چیزکیک",
                "چیزکیک سبک نیویورک. کرمی و خوشمزه، یک انتخاب کلاسیک برای دسر.",
                "12%",
                "۸٫۹۹ دلار"
            )
        )

        recyclerView4 = binding.layoutMenuRec4.menuRecycler4
        recyclerView4.adapter = menuAdapter4
        menuAdapter4.addMenuFood(menuList4)

        binding.menuChipGroup.setOnCheckedChangeListener { _, chipId ->
            if (chipId != View.NO_ID) {
                when (chipId) {
                    R.id.chip_mainCourse -> {
                        filterRecycler(
                            binding.layoutMenuRec1.menuRecycler1,
                            binding.layoutMenuRec1.recyclerTitle
                        )
                    }

                    R.id.chip_noPersian -> {
                        filterRecycler(
                            binding.layoutMenuRec2.menuRecycler2,
                            binding.layoutMenuRec2.recyclerTitle
                        )
                    }

                    R.id.chip_pitza -> {
                        filterRecycler(
                            binding.layoutMenuRec3.menuRecycler3,
                            binding.layoutMenuRec3.recyclerTitle
                        )
                    }

                    R.id.chip_sandwich -> {
                        filterRecycler(
                            binding.layoutMenuRec4.menuRecycler4,
                            binding.layoutMenuRec4.recyclerTitle
                        )
                    }
                }
            }
        }

        return binding.root
    }

    private fun filterRecycler(rec: RecyclerView, txt: TextView) {
        val allRecs = listOf(
            binding.layoutMenuRec1.menuRecycler1,
            binding.layoutMenuRec2.menuRecycler2,
            binding.layoutMenuRec3.menuRecycler3,
            binding.layoutMenuRec4.menuRecycler4,
        )

        val allTxt = listOf(
            binding.layoutMenuRec1.recyclerTitle,
            binding.layoutMenuRec2.recyclerTitle,
            binding.layoutMenuRec3.recyclerTitle,
            binding.layoutMenuRec4.recyclerTitle,
        )

        for ((index, rv) in allRecs.withIndex()) {
            rv.visibility = if (rv == rec) View.VISIBLE else View.GONE
            allTxt[index].visibility = if (rv == rec) View.VISIBLE else View.GONE
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}