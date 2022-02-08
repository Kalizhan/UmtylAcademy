package com.main.umtylacademyuser.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.ViewPager
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.SectionPagerAdapters
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayout.OnTabSelectedListener

class CourseFragment : Fragment() {

    lateinit var tabLayout: TabLayout
    private lateinit var viewPager: ViewPager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_course, container, false)

        tabLayout = view.findViewById(R.id.tab_layout)
        viewPager = view.findViewById(R.id.view_pager)
        viewPager.offscreenPageLimit = 2

        return view
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setUpViewPager(viewPager)
        tabLayout.setupWithViewPager(viewPager)
        tabLayout.addOnTabSelectedListener(object : OnTabSelectedListener {
            override fun onTabSelected(tab: TabLayout.Tab) {}
            override fun onTabUnselected(tab: TabLayout.Tab) {}
            override fun onTabReselected(tab: TabLayout.Tab) {}
        })
    }

    private fun addFragments(viewPager: ViewPager) {
        val adapters = SectionPagerAdapters(childFragmentManager)
        adapters.addFragment(MyCoursesFragment(), resources.getString(R.string.my_courses))
        adapters.addFragment(AllCoursesFragment(), resources.getString(R.string.all_courses))
        viewPager.adapter = adapters
    }

    private fun setUpViewPager(viewPager: ViewPager) {
        addFragments(viewPager)
    }
}