package patt.ball_app

import android.annotation.SuppressLint
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

class Hard_level : AppCompatActivity() {

    private lateinit var question: TextView

    private lateinit var ansA : TextView
    private lateinit var ansB : TextView
    private lateinit var ansC : TextView
    private lateinit var ansD : TextView
    private lateinit var anim1: Animation


    private lateinit var viewKonfetti: KonfettiView
    private var correctAnswers = 0

    private var index = 0

    private var hardtest = mutableListOf<HardTest>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hard_level)
        loadElements()
        loadAnimation()
        addQuestions()
        insertQuestions()
        answerChoice()

    }

    private fun loadElements() {
        question = findViewById(R.id.question)
        ansA = findViewById(R.id.answerA)
        ansB = findViewById(R.id.answerB)
        ansC = findViewById(R.id.answerC)
        ansD = findViewById(R.id.answerD)
        viewKonfetti = findViewById(R.id.konfettiView)


    }

    private fun answerChoice() {
        ansA.setOnClickListener {
            if (index < hardtest.size - 1) {
                checkAns(ansA, hardtest[index].variantA)
                index++
                insertQuestions()
            } else {
                checkAns(ansA, hardtest[index].variantA)
                showDialog()
            }
        }

        ansB.setOnClickListener {
            if (index < hardtest.size - 1) {
                checkAns(ansB, hardtest[index].variantB)
                index++
                insertQuestions()
            } else {
                checkAns(ansB, hardtest[index].variantB)
                showDialog()
            }
        }

        ansC.setOnClickListener {
            if (index < hardtest.size - 1) {
                checkAns(ansC, hardtest[index].variantC)
                index++
                insertQuestions()
            } else {
                checkAns(ansC, hardtest[index].variantC)
                showDialog()
            }
        }

        ansD.setOnClickListener {
            if (index < hardtest.size - 1) {
                checkAns(ansD, hardtest[index].variantD)
                index++
                insertQuestions()
            } else {
                checkAns(ansD, hardtest[index].variantD)
                showDialog()
            }
        }
    }

    @SuppressLint("SetTextI18n")
    private fun showDialog() {
        val dialog = Dialog(this)
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE)
        dialog.setCancelable(false)
        dialog.window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT));
        dialog.setContentView(R.layout.activity_dialog)
        val result = dialog.findViewById(R.id.result) as TextView
        result.text = correctAnswers.toString() + "/" + hardtest.size
        val restart = dialog.findViewById(R.id.restart) as ImageView
        val home = dialog.findViewById(R.id.home) as ImageView
        val menu = dialog.findViewById(R.id.menu) as ImageView
        restart.setOnClickListener {
            hardtest.shuffle()
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

    private fun checkAns(btn: TextView, variant: String) {
        if (variant == hardtest[index].answer) {
            startParty()
            correctAnswers++
        } else {
            btn.startAnimation(anim1)
        }
    }



    private fun insertQuestions() {
        question.text = hardtest[index].question
        ansA.text = hardtest[index].variantA
        ansB.text = hardtest[index].variantB
        ansC.text = hardtest[index].variantC
        ansD.text = hardtest[index].variantD
    }

    private fun addQuestions() {
        hardtest.add(
            HardTest(
                "Ronaldo has scored ... hat tricks",
                "59",
                "60",
                "61",
                "62",
                "61",

                )
        )
        hardtest.add(
            HardTest(
                "Messi have ... ballondors",
                "5",
                "6",
                "7",
                "8",
                "7",

                )
        )
        hardtest.add(
            HardTest(
                "Ronaldo scored ... goals in his carrer",
                "824",
                "825",
                "965",
                "999",
                "824",

                )
        )
        hardtest.add(
            HardTest(
                "Liverpool won ... times Champions League!",
                "5",
                "6",
                "7",
                "8",
                "6",

                )
        )
        hardtest.add(
            HardTest(
                "Who is the favourite football player of creator of this quiz)",
                "Neymar JR",
                "Zlatan",
                "Messi",
                "Firmino",
                "Firmino",

                )
        )
    }




    private fun loadAnimation() {
        anim1 = AnimationUtils.loadAnimation(this, R.anim.anim1)
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
}


