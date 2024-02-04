package kan.kis.learnAndroidApp.Presentation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import kan.kis.learnAndroidApp.R

class StartSplashScreen : AppCompatActivity() {


    lateinit var loadIcon: ImageView

    lateinit var animateLoading: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_splash_screen)
        initElement()
        initAnimate()
    }

    private fun initAnimate() {

        animateLoading = ObjectAnimator.ofFloat(loadIcon, "rotation", 0f, 360f)
        animateLoading.duration = 1000 // Animation duration in milliseconds
        animateLoading.repeatCount = 10 // Infinite rotation
        // Start the animation
        animateLoading.start()

        animateLoading.addListener(object : Animator.AnimatorListener {

            override fun onAnimationStart(animation: Animator) {

            }

            override fun onAnimationEnd(animation: Animator) {
                val intent = Intent(this@StartSplashScreen, MenuActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationCancel(animation: Animator) {
            }

            override fun onAnimationRepeat(animation: Animator) {
            }

        })
    }

    private fun initElement() {
        loadIcon = findViewById(R.id.loadImage)
    }

    override fun onDestroy() {
        super.onDestroy()
        animateLoading.cancel()
    }
}