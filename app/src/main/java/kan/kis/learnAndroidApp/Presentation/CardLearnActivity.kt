package kan.kis.learnAndroidApp.Presentation

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import kan.kis.learnAndroidApp.Presentation.frgments.KotlinBasicFragment
import kan.kis.learnAndroidApp.Presentation.frgments.KotlinBasicFragment.Companion.EXTRA_KEY_FIRST_CART
import kan.kis.learnAndroidApp.Presentation.frgments.StartAndroidFragment
import kan.kis.learnAndroidApp.Presentation.frgments.ThreadsCardFragment
import kan.kis.learnAndroidApp.Presentation.frgments.ThreadsCardFragment.Companion.EXTRA_KEY_2_CART
import kan.kis.learnAndroidApp.R

class CardLearnActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_card_learn)

//        openFragment(ThreadsCardFragment())

        checkIntents()


    }

    private fun checkIntents() {
        val thisIntent = intent.getStringExtra(KEY_OPEN)
        when(thisIntent) {
            EXTRA_KEY_FIRST_CART -> {
                openFragment(KotlinBasicFragment())
            }

            EXTRA_KEY_2_CART -> {
                openFragment(StartAndroidFragment())
            }
        }
    }
    private fun openFragment(fragment: Fragment) {
        val transactionFragment = supportFragmentManager.beginTransaction()
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