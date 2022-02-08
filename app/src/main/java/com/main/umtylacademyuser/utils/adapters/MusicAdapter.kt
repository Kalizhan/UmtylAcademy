package com.main.umtylacademyuser.utils.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.main.umtylacademyuser.R
//import com.example.umtylacademyuser.ui.fragments.MusicFragment
import com.main.umtylacademyuser.utils.models.Music
import kotlinx.android.synthetic.main.item_music.view.*

class MusicAdapter(val context: Context, private val musicList: ArrayList<Music>): RecyclerView.Adapter<MusicAdapter.MyViewHolder>() {
    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)

    var musicUrl: String = ""

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_music, parent, false)
        return MyViewHolder(view).listen { pos, _ ->
            val item = musicList[pos].musicUrl
        }
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.itemView.tvNameMusic.text = musicList[position].musicTitle
        holder.itemView.tvAuthor.text = musicList[position].musicAuthor
        holder.itemView.tvMusicDuration.text = musicList[position].musicDuration
    }

    override fun getItemCount(): Int {
        return musicList.size
    }

    fun <T : RecyclerView.ViewHolder> T.listen(event: (position: Int, type: Int) -> Unit): T {
        itemView.setOnClickListener {
            event.invoke(adapterPosition, itemViewType)
        }
        return this
    }
}