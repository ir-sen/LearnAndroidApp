package kan.kis.learnAndroidApp.Presentation

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kan.kis.learnAndroidApp.Presentation.Adapters.CardAdapter
import kan.kis.learnAndroidApp.Presentation.frgments.ActivFragMenuFragment.Companion.EXTRA_ACT_FG_M_KEY
import kan.kis.learnAndroidApp.Presentation.frgments.KotlinBasicFragment.Companion.EXTRA_KEY_FIRST_CART
import kan.kis.learnAndroidApp.Presentation.frgments.StartAndroidFragment.Companion.EXTRA_START_ANDROID
import kan.kis.learnAndroidApp.Presentation.frgments.ThreadsCardFragment.Companion.EXTRA_THREAD
import kan.kis.learnAndroidApp.databinding.ActivityMainBinding

class MenuActivity : AppCompatActivity() {


    lateinit var binding: ActivityMainBinding

    lateinit var adapterCard: CardAdapter

    private val TAG = "MenuActivityTAG"

    private lateinit var database: DatabaseReference


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        menuListeners()

        checkFirebaseDataBase()

    }



    // get info from firebase
    private fun checkFirebaseDataBase() {
        database = Firebase.database.reference
        // just check all child in fire base
        val myRef = database.child("key").child("kotlinBasic").get().addOnSuccessListener {
            Log.d(TAG, "on success: ${it.child("item2")}")
            Log.d(TAG, "on success: ${it}")
        }.addOnFailureListener {
            Log.d(TAG, "on onfailure: $it")
        }


        // get array from firebase data base
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                for (child in snapshot.children) {
                    child.children.forEach {
                        Log.d(TAG, "ChildrenChild: ${it.getValue()}")
                        Log.d(TAG, "City state: ${it.child("title")}")
                    }
                }
            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "Listeners: ${error}")
            }
        }

        database.addValueEventListener(valueEventListener)


//        myRef.setValue("hello lol")
//
        myRef.addOnCompleteListener {
            Log.d(TAG, "Complete listeners: $it")
        }
    }

    private fun menuListeners() {
        binding.mmItem1.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(this, EXTRA_KEY_FIRST_CART))
        }

        binding.mmItem2.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(this, EXTRA_START_ANDROID))
        }

        binding.mmItem3.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(this, EXTRA_ACT_FG_M_KEY))
        }

        binding.mmItem4.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(this, EXTRA_THREAD))
        }





//        binding.mmItem4.setOnClickListener().
    }

}