package com.main.umtylacademyuser.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.UniqueCourseAdapter
import com.main.umtylacademyuser.utils.models.UniqueCourse
import com.main.umtylacademyuser.utils.viewmodels.CoursesMyViewModel
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.activity_my_course.*
import kotlinx.android.synthetic.main.activity_my_course.courseDate1
import kotlinx.android.synthetic.main.buy_course_dialog.view.*

class MyCourseActivity : AppCompatActivity(), View.OnClickListener {

    private lateinit var viewModel: CoursesMyViewModel
    private var courseId = ""
    private var courseAty = ""
    private var date = ""
    private var courseLanguage = ""
    private var courseVersion = ""
    private lateinit var courseRecycler: RecyclerView
    var courseList = arrayListOf<UniqueCourse>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_my_course)

        initViews()
        passData()
        getListData()
        btnBackCourse1.setOnClickListener(this)
//        img_youtube_play.setOnClickListener(this)
    }

    private fun passData() {
        courseTitle1.text = courseAty
        courseDate1.text = date
        courseLang1.text = courseLanguage

        viewModel.getList(courseId)
    }

    private fun initViews() {
        viewModel = ViewModelProvider(this)[CoursesMyViewModel::class.java]
        courseRecycler = findViewById(R.id.courseVideoRecycler1)

        val youtubeImg = intent.getStringExtra("youtubeImg").toString()

        Glide
            .with(this)
            .load(youtubeImg)
            .centerCrop()
            .placeholder(R.drawable.courseimage)
            .into(img_youtube_play)

        courseId = intent.getStringExtra("courseId").toString()
        courseAty = intent.getStringExtra("courseAty").toString()
        date = intent.getStringExtra("date").toString()
        courseLanguage = intent.getStringExtra("courseLanguage").toString()
        courseVersion = intent.getStringExtra("courseVersion").toString()
    }

    private fun getListData() {
        courseRecycler.layoutManager = LinearLayoutManager(this)
        viewModel.lst.observe(this, Observer {
            val adapter = UniqueCourseAdapter(this, viewModel.courseList)
            courseRecycler.adapter = adapter
            adapter.setOnItemClickListener(object : UniqueCourseAdapter.onItemClickListener {
                override fun onItemClick(position: Int) {
                    val intent = Intent(this@MyCourseActivity, PlayYoutubeVideo::class.java)
                    intent.putExtra("videoUri", viewModel.courseList[position].courseVieoUri)
                    startActivity(intent)
                }
            })
        })
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
            R.id.btnBackCourse1 -> {
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
//            R.id.img_youtube_play -> {
//                val intent = Intent(this, PlayYoutubeVideo::class.java)
//                intent.putExtra("videoUri", courseVersion)
//                startActivity(intent)
//            }
        }
    }
}