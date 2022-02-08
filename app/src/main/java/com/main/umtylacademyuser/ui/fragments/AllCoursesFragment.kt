package com.main.umtylacademyuser.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.activities.CourseActivity
import com.main.umtylacademyuser.utils.adapters.AllCourseAdapter
import com.main.umtylacademyuser.utils.viewmodels.AllCourseViewModel

class AllCoursesFragment : Fragment() {

    private lateinit var allCourseViewModel: AllCourseViewModel
    private lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_all_courses, container, false)


        initViews(view)
        getList()

        return view
    }

//
//
//    private fun checkActivity(error: VolleyError) {
//        val activity = activity
//        if (activity != null && isAdded)
////            mProgressDialog.setVisibility(View.GONE);
//            if (error is NoConnectionError) {
//                val errormsg = "Error";
//                Toast.makeText(activity, errormsg, Toast.LENGTH_LONG).show();
//            }
//    }

    private fun initViews(view: View) {
        allCourseViewModel = ViewModelProvider(this)[AllCourseViewModel::class.java]
        recyclerView = view.findViewById(R.id.allCourseRecycler)
    }

    private fun getList() {
        allCourseViewModel.getListOfCourses()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        allCourseViewModel.lst.observe(requireActivity(), Observer {
//            Log.i("BBB", requireActivity().toString())
            val adapter = AllCourseAdapter(allCourseViewModel.courseList, requireContext())
            recyclerView.adapter = adapter
            adapter.setOnItemClickListener(object : AllCourseAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val item = allCourseViewModel.courseList[position]
                    val intent = Intent(context, CourseActivity::class.java)
                    intent.putExtra("courseAty", item.courseAty)
                    intent.putExtra("courseImgUri", item.courseImgUri)
                    intent.putExtra("courseDescription", item.courseDescription)
                    intent.putExtra("courseId", item.courseId)
                    intent.putExtra("courseLanguage", item.courseLanguage)
                    intent.putExtra("coursePrice", item.coursePrice)
                    intent.putExtra("courseRating", item.courseRating.toString())
                    intent.putExtra("courseSabaqSany", item.courseSabaqSany.toString())
                    intent.putExtra("courseTasks", item.courseTasks)
                    intent.putExtra("courseTime", item.courseTime)
                    intent.putExtra("courseDate", item.date)
                    intent.putExtra("courseVersion", item.courseVersion)
                    startActivity(intent)
                }

            })
        })

    }


}