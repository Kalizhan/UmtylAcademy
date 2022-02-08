package com.main.umtylacademyuser.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.models.TestCard
import kotlinx.android.synthetic.main.test_card_item.view.*

class RecyclerViewAdapter(private val recyclerDataArraylist: ArrayList<TestCard>, val mContext: Context): RecyclerView.Adapter<RecyclerViewAdapter.MyViewHolder>() {

    private lateinit var mListener: onItemClickListener

    interface onItemClickListener{
        fun onItemClick(position: Int)
    }

    fun setOnItemClickListener(listener: onItemClickListener){
        mListener = listener
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.test_card_item, parent, false)
        return MyViewHolder(view, mListener)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val recyclerData: TestCard = recyclerDataArraylist[position]
        holder.itemView.test_title.text = recyclerData.title
        holder.itemView.test_icons.setImageResource(recyclerData.imgId)

//        holder.itemView.setOnClickListener {
//            val intent = Intent(mContext,SelectedTestActivity::class.java)
//            intent.putExtra("LessonName", recyclerData.title)
//            mContext.startActivity(intent)
//        }
    }

    override fun getItemCount(): Int {
        return recyclerDataArraylist.size
    }

    class MyViewHolder(itemView: View, listener: onItemClickListener): RecyclerView.ViewHolder(itemView){
        init {
            itemView.setOnClickListener {
                listener.onItemClick(adapterPosition)
            }
        }
    }

}