package com.main.umtylacademyuser.ui.activities

import android.app.Dialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.WindowManager
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.utils.adapters.MistakeAdapter
import com.main.umtylacademyuser.utils.models.Question
import kotlinx.android.synthetic.main.activity_result.*
import kotlinx.android.synthetic.main.mistakelist.view.*

class ResultActivity : AppCompatActivity() {
    var correct: Int = 0
    var wrong: Int = 0
    var sizeQuest = ""
    var title = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        setContentView(R.layout.activity_result)

        correct = intent.getIntExtra("correct", 0)
        wrong = intent.getIntExtra("wrong", 0)
        sizeQuest = intent.getStringExtra("size")!!
        title = intent.getStringExtra("title")!!
        var mistakeList = intent.getParcelableArrayListExtra<Question>("mistakeList")

        testLessonName.text = title

        circularProgressBar.progressMax = sizeQuest.toFloat()
        circularProgressBar.setProgressWithAnimation(correct.toFloat(), 1000)

        resultText.text = "$correct/${sizeQuest}"

        btnMainPage.setOnClickListener {
            goMain()
        }

        backToTest.setOnClickListener {
            goMain()
        }

        checkMistake.setOnClickListener {
            val display = layoutInflater.inflate(R.layout.mistakelist, null)
            val dialog = Dialog(this, R.style.FullScreenDialog)
            window.setGravity(Gravity.NO_GRAVITY)

            display.mistakeRecycler.layoutManager = LinearLayoutManager(this)
            if (mistakeList!!.isEmpty()){
                Toast.makeText(this, "${resources.getString(R.string.emptyScore)}", Toast.LENGTH_SHORT).show()
            }else{
                display.tvAllQuest.text = "${resources.getString(R.string.yourQuestSize)} ${mistakeList.size}"
                display.mistakeRecycler.adapter = MistakeAdapter(this, mistakeList)
            }

            display.closeDisplay.setOnClickListener {
                dialog.cancel()
            }

            dialog.setContentView(display)
            dialog.show()
        }
    }


    private fun goMain() {
        val intent = Intent(this, MainActivity::class.java)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }
}