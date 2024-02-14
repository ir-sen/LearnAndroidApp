package kan.kis.learnAndroidApp.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kan.kis.learnAndroidApp.Presentation.frgments.*
import kan.kis.learnAndroidApp.Presentation.frgments.ActivQuestionFragment.Companion.EXTRA_ACTIV_QUESTION
import kan.kis.learnAndroidApp.Presentation.frgments.FragmentQuestionFragment.Companion.EXTRA_FRAG_QUESTION
import kan.kis.learnAndroidApp.Presentation.frgments.KotlinBasicFragment.Companion.EXTRA_KEY_FIRST_CART
import kan.kis.learnAndroidApp.Presentation.frgments.LibrariesFragment.Companion.EXTRA_LIBRARIES
import kan.kis.learnAndroidApp.Presentation.frgments.StartAndroidFragment.Companion.EXTRA_START_ANDROID
import kan.kis.learnAndroidApp.Presentation.frgments.ThreadsCardFragment.Companion.EXTRA_THREAD
import kan.kis.learnAndroidApp.R

class CardLearnActivity : AppCompatActivity() {

    // activity for open fragments

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_learn)

//        openFragment(ThreadsCardFragment())

        checkIntents()


    }

    private fun checkIntents() {
        val thisIntent = intent.getStringExtra(KEY_OPEN)
        when (thisIntent) {

            EXTRA_KEY_FIRST_CART -> {
                openFragment(KotlinBasicFragment())
            }

            EXTRA_START_ANDROID -> {
                openFragment(StartAndroidFragment())
            }

            ActivFragMenuFragment.EXTRA_ACT_FG_M_KEY -> {
                openFragment(ActivFragMenuFragment())
            }

            EXTRA_ACTIV_QUESTION -> {
                openFragment(ActivQuestionFragment())
            }

            EXTRA_FRAG_QUESTION -> {
                openFragment(FragmentQuestionFragment())
            }

            EXTRA_LIBRARIES -> {
                openFragment(LibrariesFragment())
            }

            EXTRA_THREAD -> {
                openFragment(ThreadsCardFragment())
            }


        }
    }

    private fun openFragment(fragment: Fragment) {
        val transactionFragment = supportFragmentManager.beginTransaction()
//        transactionFragment.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
        transactionFragment.setCustomAnimations(R.anim.slide_in_upp, R.anim.slide_out_upp, R.anim.slide_in_upp, R.anim.slide_out_upp,)
        transactionFragment.replace(R.id.fragment_card_container, fragment)
        transactionFragment.disallowAddToBackStack()
        transactionFragment.commit()
    }

    fun newIntent(context: Context, keyOpen: String): Intent {
        val intent = Intent(context, CardLearnActivity::class.java)
        intent.putExtra(KEY_OPEN, keyOpen)
        return intent
    }

    companion object {
        val KEY_OPEN = "keyOpenFragment"


    }


}