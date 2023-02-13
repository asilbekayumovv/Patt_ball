package patt.ball_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.widget.VideoView

class SplashActivity : AppCompatActivity() {
    private lateinit var video_view: VideoView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        video_view = findViewById(R.id.videoView)

        val videoPath = "android.resource://$packageName/raw/splashpatt"
        video_view.setVideoPath(videoPath)
        video_view.setOnCompletionListener {
            val r = Runnable {
                startActivity(Intent(this@SplashActivity, Enterence::class.java))
                finish()
            }
            Handler().postDelayed(r, 80)
        }
        video_view.start()
    }
}