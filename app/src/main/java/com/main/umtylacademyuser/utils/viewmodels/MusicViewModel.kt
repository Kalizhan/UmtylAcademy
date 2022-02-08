package com.main.umtylacademyuser.utils.viewmodels

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.main.umtylacademyuser.utils.models.Music
import com.google.firebase.database.*

class MusicViewModel(application: Application) : AndroidViewModel(application) {

    private val context = getApplication<Application>().applicationContext
    var lst = MutableLiveData<ArrayList<Music>>()
    var musicUrl = MutableLiveData<String>()
    var musicList = arrayListOf<Music>()
    private var mDbRef: DatabaseReference = FirebaseDatabase.getInstance().reference.child("music")

    fun getListOfMusic() {
        mDbRef.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                musicList.clear()
                for (postSnapshot in snapshot.children) {
                    val currentMusic = postSnapshot.getValue(Music::class.java)

                    musicList.add(currentMusic!!)
                    lst.value = musicList
                    musicUrl.value = currentMusic.musicUrl
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.i("AAA", error.message)
            }

        })
    }

    fun playMusic(){

    }

}