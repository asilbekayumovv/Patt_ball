package patt.ball_app

import android.app.Dialog
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.TextView
import nl.dionsegijn.konfetti.core.Party
import nl.dionsegijn.konfetti.core.Position
import nl.dionsegijn.konfetti.core.emitter.Emitter
import nl.dionsegijn.konfetti.xml.KonfettiView
import java.util.concurrent.TimeUnit

class Easy_level : AppCompatActivity() {
    private lateinit var question: TextView
    private lateinit var description: TextView
    private lateinit var ansA: ImageView
    private lateinit var ansB: ImageView
    private lateinit var ansC: ImageView
    private lateinit var ansD: ImageView


    private lateinit var anim1: Animation


    private lateinit var viewKonfetti: KonfettiView
    private var correctAnswers = 0

    private var index = 0
    private var lisImgTest = mutableListOf<TestImage>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_easy_level)
        loadElements()
        loadAnimation()
        addQuestions()
        insertQuestions()
        answerChoice()


    }

    private fun answerChoice() {
        ansA.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansA, lisImgTest[index].variantA)
                index++
                insertQuestions()
            } else {
                checkAns(ansA, lisImgTest[index].variantA)
                showDialog()
            }
        }

        ansB.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansB, lisImgTest[index].variantB)
                index++
                insertQuestions()
            } else {
                checkAns(ansB, lisImgTest[index].variantB)
                showDialog()
            }
        }

        ansC.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansC, lisImgTest[index].variantC)
                index++
                insertQuestions()
            } else {
                checkAns(ansC, lisImgTest[index].variantC)
                showDialog()
            }
        }

        ansD.setOnClickListener {
            if (index < lisImgTest.size - 1) {
                checkAns(ansD, lisImgTest[index].variantD)
                index++
                insertQuestions()
            } else {
                checkAns(ansD, lisImgTest[index].variantD)
                showDialog()
            }
        }
    }

    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_dialog)
        val result = dialog.findViewById(R.id.result) as TextView
        result.text = correctAnswers.toString() + "/" + lisImgTest.size
        val restart = dialog.findViewById(R.id.restart) as ImageView
        val home = dialog.findViewById(R.id.home) as ImageView
        val menu = dialog.findViewById(R.id.menu) as ImageView
        restart.setOnClickListener {
            lisImgTest.shuffle()
            correctAnswers = 0
            index = 0
            dialog.dismiss()
        }
        home.setOnClickListener {
            val intent = Intent(this, Enterence::class.java)
            startActivity(intent)
            finish()
        }
        menu.setOnClickListener {
            val intent = Intent(this, Difficulty::class.java)
            startActivity(intent)
            finish()
        }
        dialog.show()
    }

    private fun checkAns(btn: ImageView, variant: Int) {
        if (variant == lisImgTest[index].answer) {
            startParty()
            correctAnswers++
        } else {
            btn.startAnimation(anim1)
        }
    }

    private fun startParty() {
        val party = Party(
            speed = 0f,
            maxSpeed = 30f,
            damping = 0.9f,
            spread = 360,
            colors = listOf(0xfce18a, 0xff726d, 0xf4306d, 0xb48def),
            emitter = Emitter(duration = 100, TimeUnit.MILLISECONDS).max(100),
            position = Position.Relative(0.5, 0.3)
        )
        viewKonfetti.start(party)
    }

    private fun insertQuestions() {
        question.text = lisImgTest[index].question
        description.text = lisImgTest[index].description
        ansA.setImageResource(lisImgTest[index].variantA)
        ansB.setImageResource(lisImgTest[index].variantB)
        ansC.setImageResource(lisImgTest[index].variantC)
        ansD.setImageResource(lisImgTest[index].variantD)
    }

    private fun addQuestions() {
        lisImgTest.add(
            TestImage(
                "Which one is Liverpool's logo",
                "Topolsen Top",
                R.drawable.liverpool,
                R.drawable.barcelona,
                R.drawable.man_city,
                R.drawable.bayern,
                R.drawable.liverpool,

            )
        )
        lisImgTest.add(
            TestImage(
                "Which one is Man city's logo",
                "Topolsen Top",
                R.drawable.liverpool,
                R.drawable.barcelona,
                R.drawable.man_city,
                R.drawable.bayern,
                R.drawable.man_city,

                )
        )
        lisImgTest.add(
            TestImage(
                "Which one is Bayern's logo",
                "Topolsen Top",
                R.drawable.liverpool,
                R.drawable.barcelona,
                R.drawable.man_city,
                R.drawable.bayern,
                R.drawable.bayern,

                )
        )
        lisImgTest.add(
            TestImage(
                "Which one is Barcelona's logo",
                "Topolsen Top",
                R.drawable.liverpool,
                R.drawable.barcelona,
                R.drawable.man_city,
                R.drawable.bayern,
                R.drawable.barcelona,

                )
        )
        lisImgTest.add(
            TestImage(
                "Which one is Real Madrid's logo",
                "Topolsen Top",
                R.drawable.real_madrid,
                R.drawable.barcelona,
                R.drawable.man_city,
                R.drawable.liverpool,
                R.drawable.real_madrid,

                )
        )
    }

    private fun loadElements() {
        question = findViewById(R.id.question)
        description = findViewById(R.id.description)
        ansA = findViewById(R.id.ansA)
        ansB = findViewById(R.id.ansB)
        ansC = findViewById(R.id.ansC)
        ansD = findViewById(R.id.ansD)
        viewKonfetti = findViewById(R.id.konfettiView)

    }
    private fun loadAnimation()     {
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
    }
}