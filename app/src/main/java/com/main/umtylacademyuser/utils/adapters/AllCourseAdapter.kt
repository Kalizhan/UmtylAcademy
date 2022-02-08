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
import com.main.umtylacademyuser.utils.models.Course
import kotlinx.android.synthetic.main.unique_course.view.*

class AllCourseAdapter(private val courseList: ArrayList<Course>, val context: Context): RecyclerView.Adapter<AllCourseAdapter.MyViewHolder>() {
    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.unique_course, parent, false)
        return MyViewHolder(view, mListener)
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


//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, CourseActivity::class.java)
//            intent.putExtra("courseAty", item.courseAty)
//            intent.putExtra("courseImgUri", item.courseImgUri)
//            intent.putExtra("courseDescription", item.courseDescription)
//            intent.putExtra("courseId", item.courseId)
//            intent.putExtra("courseLanguage", item.courseLanguage)
//            intent.putExtra("coursePrice", item.coursePrice)
//            intent.putExtra("courseRating", item.courseRating.toString())
//            intent.putExtra("courseSabaqSany", item.courseSabaqSany.toString())
//            intent.putExtra("courseTasks", item.courseTasks)
//            intent.putExtra("courseTime", item.courseTime)
//            intent.putExtra("courseDate", item.date)
//            intent.putExtra("courseVersion", item.courseVersion)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return courseList.size
    }

}
