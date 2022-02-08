package com.main.umtylacademyuser.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.MyCoursesAdapter
import com.main.umtylacademyuser.utils.viewmodels.MyCoursesViewModel
import kotlinx.android.synthetic.main.fragment_my_courses.*

class MyCoursesFragment : Fragment() {
    private lateinit var viewModel: MyCoursesViewModel
    var str = ""
    lateinit var str_of_dostup_courses: Array<String>
    private lateinit var recyclerView: RecyclerView
//    private lateinit var progressDialog: ProgressDialog

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_my_courses, container, false)

        viewModel = ViewModelProvider(this)[MyCoursesViewModel::class.java]
        viewModel.getDostupCourseId()
        str_of_dostup_courses = emptyArray()
        recyclerView = view.findViewById(R.id.recyclerview_my_courses)
//        progressDialog = ProgressDialog(requireContext())
//        progressDialog.setCancelable(false)
        getDostupId()

        return view
    }

    private fun getDostupId() {
        viewModel.lvd.observe(requireActivity(), {
            if (it.isEmpty()){
                tv_empty_list.visibility = View.VISIBLE
                recyclerView.visibility = View.GONE
            }else{
                str_of_dostup_courses = it!!.split(",").toTypedArray()
                viewModel.getListOfMyCourses(str_of_dostup_courses)
                getListofMyCourse()
            }
        })
    }

    private fun getListofMyCourse() {
//        progressDialog.show()
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        viewModel.lst.observe(requireActivity(), {
            recyclerView.adapter = MyCoursesAdapter(requireContext(), viewModel.courseList)
        })
//        progressDialog.cancel()
    }

//    override fun onDestroy() {
//        if (progressDialog != null && progressDialog.isShowing) progressDialog.dismiss()
//        super.onDestroy()
//    }
}