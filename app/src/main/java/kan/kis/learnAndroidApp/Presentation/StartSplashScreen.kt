package kan.kis.learnAndroidApp.Presentation

import android.animation.Animator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.ScaleAnimation
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.Toast
import kan.kis.learnAndroidApp.R


@SuppressLint("CustomSplashScreen")
class StartSplashScreen : AppCompatActivity() {


    lateinit var loadIcon: LinearLayout

    lateinit var animateLoading: ObjectAnimator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_splash_screen)
//        Toast.makeText(this, "ProBobn.com!!!", Toast.LENGTH_SHORT).show()
        initElement()
        initAnimate()
    }

    private fun initAnimate() {

        val startScale = 1.0f // Initial scale factor
        val endScale = 1.7f   // Final scale factor

// Create the scale animation
        var scaleAnimation = ScaleAnimation(
            startScale,   // Start X scale
            endScale,     // End X scale
            startScale,   // Start Y scale
            endScale,     // End Y scale
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point X (center)
            Animation.RELATIVE_TO_SELF, 0.5f   // Pivot point Y (center)
        )

        scaleAnimation.duration = 2000
        scaleAnimation.repeatCount = 2

        loadIcon.startAnimation(scaleAnimation)

        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                endAnimationL()

//                val intent = Intent(this@StartSplashScreen, MenuActivity::class.java)
//                startActivity(intent)
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }

        })

//        animateLoading = ObjectAnimator.ofFloat(loadIcon, "rotation", 0f, 360f)
//        animateLoading.duration = 1000 // Animation duration in milliseconds
//        animateLoading.repeatCount = 10 // Infinite rotation
//        // Start the animation
//        animateLoading.start()
//
//        animateLoading.addListener(object : Animator.AnimatorListener {
//
//            override fun onAnimationStart(animation: Animator) {
//
//            }
//
//            override fun onAnimationEnd(animation: Animator) {
//                val intent = Intent(this@StartSplashScreen, MenuActivity::class.java)
//                startActivity(intent)
//            }
//
//            override fun onAnimationCancel(animation: Animator) {
//            }
//
//            override fun onAnimationRepeat(animation: Animator) {
//            }
//
//        })
    }

    private fun endAnimationL() {
        val startScale = 2.0f // Initial scale factor
        val endScale = 4.0f   // Final scale factor

// Create the scale animation
        var scaleAnimation = ScaleAnimation(
            startScale,   // Start X scale
            endScale,     // End X scale
            startScale,   // Start Y scale
            endScale,     // End Y scale
            Animation.RELATIVE_TO_SELF, 0.5f,  // Pivot point X (center)
            Animation.RELATIVE_TO_SELF, 0.5f   // Pivot point Y (center)
        )

        scaleAnimation.duration = 1000
        scaleAnimation.repeatCount = 1

        loadIcon.startAnimation(scaleAnimation)

        scaleAnimation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation?) {

            }

            override fun onAnimationEnd(animation: Animation?) {
                val intent = Intent(this@StartSplashScreen, MenuActivity::class.java)
                startActivity(intent)
            }

            override fun onAnimationRepeat(animation: Animation?) {
            }

        })
    }

    private fun initElement() {
        loadIcon = findViewById(R.id.cycle_anim)
    }

    override fun onDestroy() {
        super.onDestroy()
        animateLoading.cancel()
    }
}

