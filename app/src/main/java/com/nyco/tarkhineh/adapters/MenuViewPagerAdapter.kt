package com.nyco.tarkhineh.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.nyco.tarkhineh.fragments.AppetizerFragment
import com.nyco.tarkhineh.fragments.BeveragesFragment
import com.nyco.tarkhineh.fragments.DessertFragment
import com.nyco.tarkhineh.fragments.MainCourseFragment

class MenuViewPagerAdapter(fm :FragmentManager):FragmentPagerAdapter(fm) {

    override fun getItem(position: Int): Fragment {
        return when(position) {
            0-> MainCourseFragment()
            1-> AppetizerFragment()
            2-> DessertFragment()
            3-> BeveragesFragment()

            else -> throw IllegalArgumentException("Invalid position $position")
        }
    }

    override fun getCount(): Int {
        return 4
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when (position) {
            0 -> "غذای اصلی"
            1 -> "پیش غذا"
            2 -> "دسر"
            3 -> "نوشیدنی"
            else -> null
        }
    }
}