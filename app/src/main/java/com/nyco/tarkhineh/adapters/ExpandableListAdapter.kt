package com.nyco.tarkhineh.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseExpandableListAdapter
import android.widget.TextView
import com.nyco.tarkhineh.R

class ExpandableListAdapter(
    private val context: Context,
    private val titles: List<String>,
    private val bodies: List<String>
) : BaseExpandableListAdapter() {


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