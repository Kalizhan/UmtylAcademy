package com.main.umtylacademyuser.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.resource.bitmap.CenterInside
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.models.CourseRoom
import kotlinx.android.synthetic.main.unique_course.view.*

class CourseRoomAdapter(private val courseList: ArrayList<CourseRoom>, val context: Context): RecyclerView.Adapter<CourseRoomAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unique_course, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = courseList[position]
        holder.itemView.title.text = item.courseAty
        holder.itemView.ratingMedium.text = item.courseRating.toString()
        holder.itemView.price.text = item.coursePrice
        holder.itemView.ratingBar.rating = item.courseRating.toFloat()

        Glide
            .with(context)
            .load(item.courseImgUri)
            .centerCrop()
            .transform(CenterInside(), RoundedCorners(20))
            .placeholder(R.drawable.courseimage)
            .into(holder.itemView.imgToLoad)
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

}