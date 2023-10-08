package kan.kis.learnAndroidApp.Presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kan.kis.learnAndroidApp.Presentation.Adapters.CardAdapter
import kan.kis.learnAndroidApp.Presentation.KotlinBasic.Companion.EXTRA_KEY_FIRST_CART
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.Presentation.Pojo.TypeItem
import kan.kis.learnAndroidApp.Presentation.ThreadsCardFragment.Companion.EXTRA_KEY_2_CART
import kan.kis.learnAndroidApp.R
import kan.kis.learnAndroidApp.databinding.ActivityMainBinding

class MenuActivity : AppCompatActivity() {



    lateinit var binding: ActivityMainBinding

    lateinit var adapterCard: CardAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuListeners()

    }

    private fun menuListeners() {
        binding.mmItem1.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(this, EXTRA_KEY_FIRST_CART))
        }

        binding.mmItem2.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(this, EXTRA_KEY_2_CART))
        }



//        binding.mmItem4.setOnClickListener().
    }

}