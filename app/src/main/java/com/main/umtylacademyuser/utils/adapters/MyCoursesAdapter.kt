package com.main.umtylacademyuser.utils.adapters

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.activities.MyCourseActivity
import com.main.umtylacademyuser.utils.models.Course
import kotlinx.android.synthetic.main.my_courses_item.view.*

class MyCoursesAdapter(val context: Context, val list: ArrayList<Course>): RecyclerView.Adapter<MyCoursesAdapter.MyViewHolder>() {

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.my_courses_item, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]

        holder.itemView.title_my_course.text = item.courseAty
        Glide
            .with(context)
            .load(item.courseImgUri)
            .centerCrop()
            .transform(CenterInside(), RoundedCorners(20))
            .placeholder(R.drawable.courseimage)
            .into(holder.itemView.imgToLoad_my_course)

        holder.itemView.setOnClickListener {
            val intent = Intent(context, MyCourseActivity::class.java)
            intent.putExtra("courseId", item.courseId)
            intent.putExtra("courseAty", item.courseAty)
            intent.putExtra("youtubeImg", item.courseImgUri)
            intent.putExtra("date", item.date)
            intent.putExtra("courseLanguage", item.courseLanguage)
            intent.putExtra("courseVersion", item.courseVersion)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

}