package com.main.umtylacademyuser.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.models.Question
import kotlinx.android.synthetic.main.mistakeitems.view.*
import java.util.ArrayList

class MistakeAdapter(val context: Context, val list: ArrayList<Question>?): RecyclerView.Adapter<MistakeAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.mistakeitems, parent, false)
        return MyViewHolder(view)
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = list?.get(position)
        holder.itemView.tvPosition.text = "${context.resources.getString(R.string.question)} ${position+1}"
        holder.itemView.trueQuestionName.text = item?.questName
        holder.itemView.trueQuest1.text = item?.quest1
        holder.itemView.trueQuest2.text = item?.quest2
        holder.itemView.trueQuest3.text = item?.quest3
        holder.itemView.trueQuest4.text = item?.quest4

        when(item?.answ){
            holder.itemView.trueQuest1.text -> {
                holder.itemView.trueQuest1.setBackgroundResource(R.color.eqgreen)
                holder.itemView.trueQuest2.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest3.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest4.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
            }
            holder.itemView.trueQuest2.text -> {
                holder.itemView.trueQuest2.setBackgroundResource(R.color.eqgreen)
                holder.itemView.trueQuest1.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest3.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest4.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
            }
            holder.itemView.trueQuest3.text -> {
                holder.itemView.trueQuest3.setBackgroundResource(R.color.eqgreen)
                holder.itemView.trueQuest1.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest2.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest4.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
            }
            holder.itemView.trueQuest4.text -> {
                holder.itemView.trueQuest4.setBackgroundResource(R.color.eqgreen)
                holder.itemView.trueQuest1.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest2.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
                holder.itemView.trueQuest3.background = ContextCompat.getDrawable(context, R.drawable.main_button_background)
            }
        }

        holder.itemView.tvChosenAnsw.text = "${context.resources.getString(R.string.your_chose)}: ${item?.chosenAnsw}"
    }

    override fun getItemCount(): Int {
        return list!!.size
    }
}