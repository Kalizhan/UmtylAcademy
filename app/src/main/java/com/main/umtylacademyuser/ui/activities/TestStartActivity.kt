package com.main.umtylacademyuser.ui.activities

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.TextView
import com.main.umtylacademyuser.MainActivity
import com.main.umtylacademyuser.R
import com.main.umtylacademyuser.ui.activities.SelectedTestActivity.Companion.list
import com.main.umtylacademyuser.utils.models.Question
import kotlinx.android.synthetic.main.activity_test_start.*
import java.util.*
import kotlin.collections.ArrayList

class TestStartActivity : AppCompatActivity() {
    var total = 0
    var correct = 0
    var incorrect = 0
    var seconds = ""
    var dbLessonTitle = ""
    lateinit var countdown_timer: CountDownTimer
    var isRunning: Boolean = false;

    var index = 0
    lateinit var question: Question
    lateinit var allQuestionList: ArrayList<Question>

    var correctCount = 0
    var wrongCount = 0
    var sizeQuest = ""
    var sizeList = 0

    var mistakeList: ArrayList<Question> = ArrayList()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.setFlags(
            WindowManager.LayoutParams.FLAG_SECURE,
            WindowManager.LayoutParams.FLAG_SECURE
        );
        setContentView(R.layout.activity_test_start)

        initViews()
        setAllData()

        seconds = intent.getStringExtra("minutes")!!
        val int = seconds.toInt() * 60
        dbLessonTitle = intent.getStringExtra("lessonTitleTest")!!
        sizeQuest = intent.getStringExtra("size")!!

        reverseTimer(int.toLong(), timerBtn)

        backToTest.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            startActivity(intent)
        }

        btnFinishTest.setOnClickListener {
            countdown_timer.cancel()
            isRunning = false
            val myIntent = Intent(this@TestStartActivity, ResultActivity::class.java)
            myIntent.putExtra("correct", correctCount)
            myIntent.putExtra("wrong", wrongCount)
            myIntent.putExtra("size", sizeQuest)
            myIntent.putExtra("title", dbLessonTitle)
            myIntent.putParcelableArrayListExtra("mistakeList", mistakeList)
            finish()
            startActivity(myIntent)
        }
    }

    private fun initViews() {
        allQuestionList = list
        allQuestionList.shuffle()
        sizeList = allQuestionList.size
        if (sizeList>40){
            sizeList = 40
        }
        question = allQuestionList[index]
//        btnNextQuestion.isClickable = false
    }


    private fun setAllData() {
        questionName.text = question.questName
        quest1.text = question.quest1
        quest2.text = question.quest2
        quest3.text = question.quest3
        quest4.text = question.quest4
    }

    private fun reverseTimer(seconds: Long, tv: TextView) {
        countdown_timer = object : CountDownTimer(seconds * 1000, 1000) {
            override fun onTick(p0: Long) {

                val minute = (p0 / 1000) / 60
                val seconds2 = (p0 / 1000) % 60

                tv.text = "$minute:$seconds2"
            }

            override fun onFinish() {
                tv.text = "Completed"
                val myIntent = Intent(this@TestStartActivity, ResultActivity::class.java)
                myIntent.putExtra("correct", correctCount)
                myIntent.putExtra("wrong", wrongCount)
                myIntent.putExtra("size", sizeQuest)
                myIntent.putExtra("title", dbLessonTitle)
                myIntent.putParcelableArrayListExtra("mistakeList", mistakeList)
                finish()
                startActivity(myIntent)
            }
        }.start()

        isRunning = true
    }

    private fun correct(btn: Button) {
        btn.setBackgroundColor(resources.getColor(R.color.blue))
        btn.setTextColor(Color.WHITE)
//        btnNextQuestion.setOnClickListener {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                correctCount++
                index++
                question = allQuestionList[index]
                resetColor()
                setAllData()
                enableButton()
            }
        }.start()
//        }
    }

    private fun wrong(btn: Button) {
        btn.setBackgroundColor(resources.getColor(R.color.blue))
        btn.setTextColor(Color.WHITE)

//        btnNextQuestion.setOnClickListener {
        object : CountDownTimer(1000, 1000) {
            override fun onTick(millisUntilFinished: Long) {

            }
            override fun onFinish() {
                wrongCount++
                if (index < sizeList - 1) {
                    index++
                    question = allQuestionList[index]
                    resetColor()
                    setAllData()
                    enableButton()
                } else {
                    gameWon()
                }
            }
        }.start()

//        }
    }

    private fun gameWon() {
        val intent = Intent(this, ResultActivity::class.java)
        intent.putExtra("correct", correctCount)
        intent.putExtra("wrong", wrongCount)
        intent.putExtra("size", sizeQuest)
        intent.putExtra("title", dbLessonTitle)
        intent.putParcelableArrayListExtra("mistakeList", mistakeList)
        intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
        startActivity(intent)
    }

    private fun enableButton() {
        quest1.isClickable = true
        quest2.isClickable = true
        quest3.isClickable = true
        quest4.isClickable = true
    }

    private fun disableButton() {
        quest1.isClickable = false
        quest2.isClickable = false
        quest3.isClickable = false
        quest4.isClickable = false
    }

    private fun resetColor() {
        quest1.background = resources.getDrawable(R.drawable.white_second)
        quest1.setTextColor(resources.getColor(R.color.project_main))
        quest2.background = resources.getDrawable(R.drawable.white_second)
        quest2.setTextColor(resources.getColor(R.color.project_main))
        quest3.background = resources.getDrawable(R.drawable.white_second)
        quest3.setTextColor(resources.getColor(R.color.project_main))
        quest4.background = resources.getDrawable(R.drawable.white_second)
        quest4.setTextColor(resources.getColor(R.color.project_main))
    }

    fun OptionAClick(view: View) {
        disableButton()
//        btnNextQuestion.isClickable = true
//        mistakeList[index].chosenAnsw = quest1.text.toString()
        question.chosenAnsw = quest1.text.toString()
        mistakeList.add(question)
        if (question.quest1 == question.answ) {
//            quest1.setBackgroundColor(resources.getColor(R.color.blue))
            if (index < sizeList - 1) {
                correct(quest1)
            } else {
                gameWon()
            }

        } else {
            wrong(quest1)
        }
    }

    fun OptionBClick(view: View) {
        disableButton()
//        btnNextQuestion.isClickable = true
//        mistakeList[index].chosenAnsw = quest2.text.toString()
        question.chosenAnsw = quest2.text.toString()
        mistakeList.add(question)
        if (question.quest2 == question.answ) {
//            quest2.setBackgroundColor(resources.getColor(R.color.blue))
            if (index < sizeList - 1) {
                correct(quest2)
            } else {
                gameWon()
            }

        } else {
            wrong(quest2)
        }
    }

    fun OptionCClick(view: View) {
        disableButton()
//        btnNextQuestion.isClickable = true
//        mistakeList[index].chosenAnsw = quest3.text.toString()
        question.chosenAnsw = quest3.text.toString()
        mistakeList.add(question)
        if (question.quest3 == question.answ) {
//            quest3.setBackgroundColor(resources.getColor(R.color.blue))
            if (index < sizeList - 1) {
                correct(quest3)
            } else {
                gameWon()
            }

        } else {
            wrong(quest3)
        }
    }

    fun OptionDClick(view: View) {
        disableButton()
//        btnNextQuestion.isClickable = true
//        mistakeList[index].chosenAnsw = quest4.text.toString()
        question.chosenAnsw = quest4.text.toString()
        mistakeList.add(question)

        if (question.quest4 == question.answ) {
//            quest4.setBackgroundColor(resources.getColor(R.color.blue))
            if (index < sizeList - 1) {
                correct(quest4)
            } else {
                gameWon()
            }
        } else {
            wrong(quest4)
        }
    }

    override fun onStop() {
        super.onStop()
        countdown_timer.cancel()
        isRunning = false
    }
}