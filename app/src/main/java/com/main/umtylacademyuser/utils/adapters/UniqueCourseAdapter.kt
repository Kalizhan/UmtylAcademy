package com.main.umtylacademyuser.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.models.UniqueCourse
import kotlinx.android.synthetic.main.course_recycler.view.*

class UniqueCourseAdapter(val context: Context, private val list: ArrayList<UniqueCourse>): RecyclerView.Adapter<UniqueCourseAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.course_recycler, parent, false)
        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list[position]
        holder.itemView.courseTitle12.text = item.courseTitle
        holder.itemView.courseOrder.text = "${item.courseOrder}"

//        holder.itemView.setOnClickListener {
//            val intent = Intent(context, PlayYoutubeVideo::class.java)
//            intent.putExtra("videoUri", item.courseVieoUri)
//            context.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return list.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }
}