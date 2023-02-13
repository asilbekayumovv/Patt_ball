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

class Mid_Level : AppCompatActivity() {

    private lateinit var question: TextView
    private lateinit var image: ImageView
    private lateinit var ansA : TextView
    private lateinit var ansB : TextView
    private lateinit var ansC : TextView
    private lateinit var ansD : TextView
    private lateinit var anim1: Animation


    private lateinit var viewKonfetti: KonfettiView
    private var correctAnswers = 0

    private var index = 0

    private var playerTest = mutableListOf<Player_test>()
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mid_level)
        loadElements()
        loadAnimation()
        addQuestions()
        insertQuestions()
        answerChoice()

    }

    private fun loadElements() {
        question = findViewById(R.id.question)
        image = findViewById(R.id.imageView)
        ansA = findViewById(R.id.answerA)
        ansB = findViewById(R.id.answerB)
        ansC = findViewById(R.id.answerC)
        ansD = findViewById(R.id.answerD)
        viewKonfetti = findViewById(R.id.konfettiView)


    }

    private fun answerChoice() {
        ansA.setOnClickListener {
            if (index < playerTest.size - 1) {
                checkAns(ansA, playerTest[index].variantA)
                index++
                insertQuestions()
            } else {
                checkAns(ansA, playerTest[index].variantA)
                showDialog()
            }
        }

        ansB.setOnClickListener {
            if (index < playerTest.size - 1) {
                checkAns(ansB, playerTest[index].variantB)
                index++
                insertQuestions()
            } else {
                checkAns(ansB, playerTest[index].variantB)
                showDialog()
            }
        }

        ansC.setOnClickListener {
            if (index < playerTest.size - 1) {
                checkAns(ansC, playerTest[index].variantC)
                index++
                insertQuestions()
            } else {
                checkAns(ansC, playerTest[index].variantC)
                showDialog()
            }
        }

        ansD.setOnClickListener {
            if (index < playerTest.size - 1) {
                checkAns(ansD, playerTest[index].variantD)
                index++
                insertQuestions()
            } else {
                checkAns(ansD, playerTest[index].variantD)
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
        result.text = correctAnswers.toString() + "/" + playerTest.size
        val restart = dialog.findViewById(R.id.restart) as ImageView
        val home = dialog.findViewById(R.id.home) as ImageView
        val menu = dialog.findViewById(R.id.menu) as ImageView
        restart.setOnClickListener {
            playerTest.shuffle()
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
        if (variant == playerTest[index].answer) {
            startParty()
            correctAnswers++
        } else {
            btn.startAnimation(anim1)
        }
    }



    private fun insertQuestions() {
        question.text = playerTest[index].question
        image.setImageResource(playerTest[index].image)
        ansA.text = playerTest[index].variantA
        ansB.text = playerTest[index].variantB
        ansC.text = playerTest[index].variantC
        ansD.text = playerTest[index].variantD
    }

    private fun addQuestions() {
        playerTest.add(
            Player_test(
                "Who is this player",
                R.drawable.ronaldo,
                "He is 38 y.o",
                "Cristiano Ronaldo",
                "Leo Messi",
                "Neymar JR",
                "Mohammed Salah",
                "Cristiano Ronaldo",

            )
        )
        playerTest.add(
            Player_test(
                "Who is this player",
                R.drawable.salah,
                "He is 31 y.o",
                "Cristiano Ronaldo",
                "Mohammed Salah",
                "Neymar JR",
                "Leo Messi",
                "Mohammed Salah",

                )
        )
        playerTest.add(
            Player_test(
                "Who is this player",
                R.drawable.neymar,
                "He is 31 y.o",
                "Cristiano Ronaldo",
                "Leo Messi",
                "Neymar JR",
                "Mohammed Salah",
                "Neymar JR",

                )
        )
        playerTest.add(
            Player_test(
                "Who is this player",
                R.drawable.messi,
                "He is 35 y.o",
                "Cristiano Ronaldo",
                "Leo Messi",
                "Zlatan",
                "Mohammed Salah",
                "Leo Messi",

                )
        )
        playerTest.add(
            Player_test(
                "Who is this player",
                R.drawable.zlatan,
                "He is 41 y.o",
                "Neymar JR",
                "Zlatan",
                "Cristiano Ronaldo",
                "Mohammed Salah",
                "Zlatan",

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

private fun ImageView.setImageResource(image: ImageView) {

}
