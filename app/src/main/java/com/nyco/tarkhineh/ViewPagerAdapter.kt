package com.nyco.tarkhineh

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.viewpager.widget.PagerAdapter


class ViewPagerAdapter(private val context: Context): PagerAdapter() {


    private val characters = listOf(
        R.drawable.character1,
        R.drawable.character2,
        R.drawable.character3
    )

    private val headers = listOf(
        R.string.head1,
        R.string.head2,
        R.string.head3
    )

    private val descs = listOf(
        R.string.desc1,
        R.string.desc2,
        R.string.desc3
    )

    override fun getCount(): Int {
        return headers.size
    }

    override fun isViewFromObject(view: View, `object`: Any): Boolean {
        return view == `object` as LinearLayout
    }

    override fun instantiateItem(container: ViewGroup, position: Int): Any {

        val layoutInflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = layoutInflater.inflate(R.layout.onboarding_content_layout, container, false)

        val onBoardingHead = view.findViewById<TextView>(R.id.onBoarding_head)
        val onBoardingDesc = view.findViewById<TextView>(R.id.onBoarding_desc)
        val onBoardingChar = view.findViewById<ImageView>(R.id.onBoarding_char)

        onBoardingHead.setText(headers[position])
        onBoardingDesc.setText(descs[position])
        onBoardingChar.setImageResource(characters[position])

        container.addView(view)

        return view
    }

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

}