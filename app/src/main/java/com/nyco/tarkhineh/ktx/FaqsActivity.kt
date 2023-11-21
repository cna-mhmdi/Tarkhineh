package com.nyco.tarkhineh.ktx

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.nyco.tarkhineh.R
import com.nyco.tarkhineh.adapters.ExpandableListAdapter
import com.nyco.tarkhineh.databinding.ActivityFaqsBinding

class FaqsActivity : AppCompatActivity() {

    private val titles: List<String> = listOf(
        "امکانات ترخینه",
        "حساب کاربری",
        "سابقه خرید",
        "راه‌های پرداخت",
        "تفاوت قیمت در منو شعبات و منو وبسایت",
        "هدیه و تخفیف"
    )
    private val bodies: List<String> = listOf(
        "ترخینه با قابلیت انتخاب از منو و ارائه گزینه‌های غذایی متنوع، تجربه‌ای را برای کاربران به ارمغان می‌آورد. خدمات مدیریت سفارشات و تحویل سریع نیز جزو امکانات این اپلیکیشن رستوران می‌باشد.",
        "با امکان ثبت اطلاعات کاربری، ترخینه به کاربران این اطمینان را می‌دهد که اطلاعاتشان در امان و محفوظ است.",
        "سابقه خریدها به طور دائمی ذخیره می‌شود و در بخش سوابق، کاربران می‌توانند به راحتی سابقه خریدهای خود را مرور کرده و چک کنند.",
        "ترخینه با ارائه چندین راه پرداخت مختلف، امکان انتخاب گزینه‌های متنوع را فراهم می‌کند. در ضمن، از درگاه امن زرین‌پال برای پرداخت‌ها استفاده می‌شود.",
        "ترخینه با افتخار اعلام می‌کند که هیچ تفاوتی در قیمت‌گذاری بین منوی شعبات و منوی وبسایت ندارد. این تضمین از انصاف و شفافیت در ارائه خدمات به مشتریان خود خبر می‌دهد.",
        "ترخینه در مناسبت‌های خاص، تخفیفات و هدایا را به مشتریان خود ارائه می‌دهد. این اقدامات به منظور جلب رضایت و ارتقاء تجربه خرید کاربران اجرا می‌شوند."
    )

    private lateinit var binding: ActivityFaqsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFaqsBinding.inflate(layoutInflater)
        setContentView(binding.root)


        val adapter = ExpandableListAdapter(this,titles,bodies)
        binding.expandableFaqs.setAdapter(adapter)

        setSupportActionBar(binding.faqsToolbar)
        supportActionBar?.title = null

        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        binding.faqsToolbar.setNavigationIcon(R.drawable.arrow_left1)

    }

    override fun onSupportNavigateUp(): Boolean {
        onBackPressed()
        return true
    }
}