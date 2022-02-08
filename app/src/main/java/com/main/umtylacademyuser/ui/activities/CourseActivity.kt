package com.main.umtylacademyuser.ui.activities

import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.UniqueCourseAdapter
import com.main.umtylacademyuser.utils.models.UniqueCourse
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_course.*
import kotlinx.android.synthetic.main.buy_course_dialog.*
import kotlinx.android.synthetic.main.buy_course_dialog.view.*
import com.bumptech.glide.Glide
import com.main.umtylacademyuser.utils.models.CourseRoom
import com.main.umtylacademyuser.utils.viewmodels.CourseViewModel
import kotlinx.android.synthetic.main.unique_course.view.*

import android.net.Uri
import java.lang.Exception


class CourseActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var courseViewModel: CourseViewModel
    private lateinit var courseAty: String
    private lateinit var courseDescription: String
    private lateinit var courseId: String
    private lateinit var courseLanguage: String
    private lateinit var coursePrice: String
    private lateinit var courseRating: String
    private lateinit var courseSabaqSany: String
    private lateinit var courseTasks: String
    private lateinit var courseTime: String
    private lateinit var courseDate: String
    private lateinit var courseImgUri: String
    private lateinit var courseVersion: String
    private var list = listOf<String>()
    private lateinit var courseTask1: String
    private lateinit var courseTask2: String
    private lateinit var courseTask3: String
    private lateinit var courseRecycler: RecyclerView

    var courseList = arrayListOf<UniqueCourse>()

    lateinit var view: View
    lateinit var dialog: Dialog


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_course)

//        youtube_img.setOnClickListener(this)
        btnBackCourse.setOnClickListener(this)
        courseBtnBuy.setOnClickListener(this)
        btnBuyCourse2.setOnClickListener(this)
        courseBtnAddToCart.setOnClickListener(this)

        initViews()
        passData()
        observeUniqueCourseList()
    }

    private fun initViews() {
        courseViewModel = ViewModelProvider(this)[CourseViewModel::class.java]
        courseRecycler = findViewById(R.id.courseVideoRecycler)

        courseAty = intent.getStringExtra("courseAty")!!
        courseDescription = intent.getStringExtra("courseDescription")!!
        courseImgUri = intent.getStringExtra("courseImgUri")!!
        courseId = intent.getStringExtra("courseId")!!
        courseLanguage = intent.getStringExtra("courseLanguage")!!
        coursePrice = intent.getStringExtra("coursePrice")!!
        courseRating = intent.getStringExtra("courseRating")!!
        courseSabaqSany = intent.getStringExtra("courseSabaqSany")!!
        courseTasks = intent.getStringExtra("courseTasks")!!
        courseTime = intent.getStringExtra("courseTime")!!
        courseDate = intent.getStringExtra("courseDate")!!
        courseVersion = intent.getStringExtra("courseVersion")!!

        Glide
            .with(this)
            .load(courseImgUri)
            .centerCrop()
            .placeholder(R.drawable.courseimage)
            .into(youtube_img)

        list = courseTasks.split(",")
        courseTask1 = list[0]
        courseTask2 = list[1]
        courseTask3 = list[2]

        courseViewModel.listOfCourses(courseId)
    }

//    private fun progressDialog() {
//        val progressDialog = ProgressDialog(this)
//        progressDialog.setCancelable(false)
//        progressDialog.setTitle(resources.getString(R.string.loading))
//        progressDialog.setMessage(resources.getString(R.string.pls_wait))
//        progressDialog.show()
//        Toast.makeText(this, resources.getString(R.string.check_course), Toast.LENGTH_SHORT)
//            .show()
//        progressDialog.cancel()
//    }

    fun dialogFun() {

        view = layoutInflater.inflate(R.layout.buy_course_dialog, null)
        dialog = Dialog(this@CourseActivity, R.style.FullScreenDialog)
        window.setGravity(Gravity.NO_GRAVITY)
        view.tv_pass_more_data.text =
            "${resources.getString(R.string.first_part)} <<${courseAty}>> ${resources.getString(R.string.second_part)}"
        dialog.setContentView(view)
        view.backToCourse.setOnClickListener {
            dialog.cancel()
        }
        view.whatsNumber.setOnClickListener {
            sendMessage("Хочу купить курс $courseAty")
        }

        dialog.show()
    }

    fun sendMessage(message: String) {
        try {
            val toNumber = "77076667585"
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("http://api.whatsapp.com/send?phone=$toNumber&text=$message")
            startActivity(intent)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun passData() {
        courseTitle.text = courseAty
        courseRating1.text = courseRating
        courseRatingBar.rating = courseRating.toFloat()
        courseLang.text = courseLanguage
        courseDate1.text = courseDate
        coursePrice1.text = coursePrice
        coursePrice2.text = coursePrice
        courseTask11.text = courseTask1
        courseTask22.text = courseTask2
        courseTask33.text = courseTask3
        courseSabaqSany1.text = courseSabaqSany
        courseTime1.text = courseTime
    }

    private fun observeUniqueCourseList() {
        courseRecycler.layoutManager = LinearLayoutManager(this)
        courseViewModel.lst.observe(this, Observer {
            val adapter = UniqueCourseAdapter(this, courseViewModel.courseList)
            courseRecycler.adapter = adapter
            Log.i("AAA", courseViewModel.courseList.toString())
            adapter.setOnItemClickListener(object : UniqueCourseAdapter.onItemClickListener{
                override fun onItemClick(position: Int) {
//                    Toast.makeText(this@CourseActivity, resources.getString(R.string.to_view_buy_course), Toast.LENGTH_SHORT).show()
                }
            })
        })
    }

    override fun onClick(p0: View?) {
        when(p0!!.id){
//            R.id.youtube_img -> {
//                val intent1 = Intent(this, PlayYoutubeVideo::class.java)
//                intent1.putExtra("videoUri", courseVersion)
//                startActivity(intent1)
//            }
            R.id.btnBackCourse -> {
                val intent = Intent(this, MainActivity::class.java)
                finish()
                startActivity(intent)
            }
            R.id.courseBtnBuy -> {
                buyCourse()
            }
            R.id.btnBuyCourse2 -> {
                buyCourse()
            }
            R.id.courseBtnAddToCart -> {
                val course = CourseRoom(
                    courseId,
                    courseAty,
                    courseImgUri,
                    coursePrice,
                    courseRating.toDouble()
                )
                courseViewModel.addCourse(course)
                Toast.makeText(this, resources.getString(R.string.added_to_cart), Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun buyCourse(){
        dialogFun()
//        var ssst = ""
//        courseViewModel.buyCourse()
//        courseViewModel.lvd.observe(this, Observer {
//            Log.i("BBB", it)
//            ssst = it
//        })
//        var listDostups = ssst.split(",")
//        for (element in listDostups){
//            var element1 = element.replace(" ", "")
//            Log.i("BBB1", element1)
//            if (element1 == courseId){
//                Toast.makeText(this, resources.getString(R.string.haded), Toast.LENGTH_SHORT).show()
//            }else{
//                dialogFun()
//            }
//        }
//        dialogFun()
//        if (ssst == "dialog" || ssst.isEmpty()){
//            dialogFun()
//        }else{
//            Toast.makeText(this, resources.getString(R.string.haded), Toast.LENGTH_SHORT).show()
////            progressDialog()
//        }
    }
}