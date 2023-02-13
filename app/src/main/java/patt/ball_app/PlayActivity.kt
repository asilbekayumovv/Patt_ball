package patt.ball_app

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView

class PlayActivity : AppCompatActivity() {

    private lateinit var play: ImageView
    private lateinit var settings: ImageView
    private lateinit var menu: ImageView

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_play)

        play = findViewById(R.id.playBtn)
        settings = findViewById(R.id.settings)
        menu = findViewById(R.id.menu)


play.setOnClickListener{
val intent = Intent(this, Difficulty::class.java)
    startActivity(intent)
}







    }
}