package com.nyco.tarkhineh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView

class ExpandableListAdapter(private val context: Context) : BaseExpandableListAdapter() {
    private val titles: List<String> = listOf(
        "حداقال سفارش",
        "فاصله تحویل",
        "زمان تحویل",
        "گزینه\u200Cهای پرداخت",
        "دقت سفارش",
        "شرایط لغو سفارش",
        "شرایط بازگشت سفارش",
        "تخفیفات"
    )

    private val bodies: List<String> = listOf(
        "کاربران ملزم به رعایت حداقل مقدار سفارش تعیین شده برای استفاده از خدمات اپلیکیشن هستند. این مقدار می\u200Cتواند بر اساس نوع محصولات و منطقه تحویل متغیر باشد. عدم رعایت این مقدار می\u200Cتواند منجر به عدم پذیرش سفارش یا ایجاد محدودیت\u200Cهایی در فرآیند سفارش\u200Cدهی شود.",
        "فاصله تحویل مشخص می\u200Cکند که کاربران باید در چه محدوده جغرافیایی قرار داشته باشند تا بتوانند از خدمات تحویل سفارش استفاده کنند. عدم تطابق موقعیت جغرافیایی با محدوده تحویل ممکن است به عدم توانایی در سفارش دادن منجر شود.",
        "زمان تحویل سفارش\u200Cها بر اساس نوع محصولات و موقعیت جغرافیایی مشتریان تعیین می\u200Cشود. اپلیکیشن به کاربران این اطلاعات را ارائه می\u200Cدهد تا بتوانند زمان تحویل دلخواه خود را انتخاب کنند. کاربران باید زمان تحویل را با دقت انتخاب کنند تا به تحویل به موقع سفارش\u200Cها کمک کنند.",
        "کاربران می\u200Cتوانند از گزینه\u200Cهای پرداخت مختلفی مانند پرداخت نقدی، پرداخت آنلاین از طریق کارت اعتباری و دیگر روش\u200Cهای پرداخت استفاده کنند. هر یک از این گزینه\u200Cها شرایط و قوانین مربوط به خود را دارند و کاربران باید این شرایط را رعایت کنند.",
        "اپلیکیشن تضمین می\u200Cکند که سفارش\u200Cها با دقت تهیه شده و به آدرس مشتری منتقل می\u200Cشوند. در صورتی که سفارش تطابقی با سفارش کاربر نداشته باشد، کاربران می\u200Cتوانند از حق خود برای اصلاح سفارش استفاده کنند.",
        "کاربران باید به شرایط و قوانین مربوط به لغو سفارش توجه داشته باشند. این شرایط ممکن است شامل مهلت\u200Cهای لغو و هزینه\u200Cهای مرتبط با لغو سفارش باشد. در صورت نیاز به لغو سفارش، کاربران باید شرایط مربوط را رعایت کنند.",
        "در صورتی که کاربران تصمیم به بازگشت سفارش داشته باشند، باید شرایط و قوانین مربوط به بازگشت سفارش را رعایت کنند. این شامل زمان بازگشت، حالت سفارش و شرایط مرتبط با بازگشت ممکن است.",
        "این اپلیکیشن تخفیفات و پیشنهادات ویژه را برای کاربران ارائه می\u200Cدهد. این تخفیفات ممکن است بر اساس نوع محصولات، مقدار سفارش و زمان تحویل متغیر باشد. کاربران می\u200Cتوانند از این تخفیفات برای صرفه\u200Cجویی در هزینه\u200Cهای سفارش\u200Cهای خود بهره\u200Cبرند."
    )

    override fun getGroup(groupPosition: Int): Any {
        return titles[groupPosition]
    }

    override fun isChildSelectable(groupPosition: Int, childPosition: Int): Boolean {
        return true
    }

    override fun hasStableIds(): Boolean {
        return true
    }

    override fun getGroupCount(): Int {
        return titles.size
    }

    override fun getChildView(
        groupPosition: Int,
        childPosition: Int,
        isLastChild: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val itemView: View = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.expandable_child_list, parent, false)

        val textView = itemView.findViewById<TextView>(R.id.bodyPrivacy)
        textView.text = bodies[groupPosition]

        return itemView
    }

    override fun getGroupId(groupPosition: Int): Long {
        return groupPosition.toLong()
    }

    override fun getChild(groupPosition: Int, childPosition: Int): Any {
        return bodies.size
    }

    override fun getChildrenCount(groupPosition: Int): Int {
        return 1
    }

    override fun getChildId(groupPosition: Int, childPosition: Int): Long {
        return childPosition.toLong()
    }

    override fun getGroupView(
        groupPosition: Int,
        isExpanded: Boolean,
        convertView: View?,
        parent: ViewGroup?,
    ): View {
        val groupView: View = convertView
            ?: LayoutInflater.from(context).inflate(R.layout.expandable_group_list, parent, false)

        val groupTextView = groupView.findViewById<TextView>(R.id.titlePrivacy)
        groupTextView.text = titles[groupPosition]

        return groupView
    }
}