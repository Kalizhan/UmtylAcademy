package com.main.umtylacademyuser.ui.activities

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.models.Question
import com.google.firebase.database.*
import kotlinx.android.synthetic.main.activity_selected_test.*

class SelectedTestActivity : AppCompatActivity() {

    private lateinit var mDatabaseReference: DatabaseReference
    private lateinit var lessonTitle: String
    private var minutes = 0
    var size = 0
    var lang = ""

    companion object{
        var list: ArrayList<Question> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_selected_test)

        initViews()

        back.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            finish()
            startActivity(intent)
        }

        btnStartTest.setOnClickListener{
            if (size == 0){
                Toast.makeText(this, resources.getString(R.string.empty), Toast.LENGTH_SHORT).show()
            }else{
                val intent = Intent(this, TestStartActivity::class.java)
                finish()
                intent.putExtra("lessonTitleTest", lessonTitle)
                intent.putExtra("minutes", minutes.toString())
                intent.putExtra("size", size.toString())
                startActivity(intent)
            }
        }
    }

    private fun initViews() {
        list = ArrayList()


        lessonTitle = intent.getStringExtra("LessonName")!!
        lang = intent.getStringExtra("lang")!!

        if (lang.isEmpty()){
            lang = "ru"
        }
        lessonName.text = lessonTitle
        var dbChildName = ""

        Log.i("AAAAA", lang)

        when(lessonTitle){
            resources.getString(R.string.algebra) -> dbChildName = "algebra"
            resources.getString(R.string.attestat) -> dbChildName = "attestat"
            resources.getString(R.string.belbin_test) -> dbChildName = "belbin_test"
            resources.getString(R.string.biology) -> dbChildName = "biology"
            resources.getString(R.string.chemistry) -> dbChildName = "chemistry"
            resources.getString(R.string.eng_lang) -> dbChildName = "eng_lang"
            resources.getString(R.string.geography) -> dbChildName = "geography"
            resources.getString(R.string.geography2) -> dbChildName = "geography2"
            resources.getString(R.string.geometry) -> dbChildName = "geometry"
            resources.getString(R.string.inform) -> dbChildName = "inform"
            resources.getString(R.string.kval_test) -> dbChildName = "kval_test"
            resources.getString(R.string.kz_history) -> dbChildName = "kz_history"
            resources.getString(R.string.kz_lang) -> dbChildName = "kz_lang"
            resources.getString(R.string.kz_literature) -> dbChildName = "kz_literature"
            resources.getString(R.string.law) -> dbChildName = "law"
            resources.getString(R.string.logic_questions) -> dbChildName = "logic_questions"
            resources.getString(R.string.management) -> dbChildName = "management"
            resources.getString(R.string.math) -> dbChildName = "math"
            resources.getString(R.string.metodist_test) -> dbChildName = "metodist_test"
            resources.getString(R.string.music) -> dbChildName = "music"
            resources.getString(R.string.oz_oz) -> dbChildName = "oz_oz"
            resources.getString(R.string.physics) -> dbChildName = "physics"
            resources.getString(R.string.primary_class) -> dbChildName = "primary_class"
            resources.getString(R.string.psychology) -> dbChildName = "psychology"
            resources.getString(R.string.read_property) -> dbChildName = "read_property"
            resources.getString(R.string.ru_lang) -> dbChildName = "ru_lang"
            resources.getString(R.string.technology) -> dbChildName = "technology"
            resources.getString(R.string.world_history) -> dbChildName = "world_history"
        }

//        val sharedPreferences = getSharedPreferences("Language", Context.MODE_PRIVATE)
//        val language = sharedPreferences.getString("My_Lang", "ru")
//        setLocate(language.toString())

        mDatabaseReference = FirebaseDatabase.getInstance().getReference("tests").child(lang).child(dbChildName)

        mDatabaseReference.addValueEventListener(object : ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                minutes = snapshot.childrenCount.toInt()
                if (minutes > 40){
                    minutes = 40
                }
                size = snapshot.childrenCount.toInt()
                if (size>40){
                    size = 40
                }
                questSize.text = "$size ${resources.getString(R.string.questions)}"
                questTime.text = "$minutes минут"

                for (snap: DataSnapshot in snapshot.children) {
                    val question = snap.getValue(Question::class.java)
                    list.add(question!!)
                }
            }

            override fun onCancelled(error: DatabaseError) {
                throw error.toException();
            }
        })
    }
}