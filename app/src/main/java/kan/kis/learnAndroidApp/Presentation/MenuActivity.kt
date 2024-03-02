package kan.kis.learnAndroidApp.Presentation

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkCapabilities
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.WindowManager
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
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
import kan.kis.learnAndroidApp.R
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


        if (checkForInternet(this)) {
//            Toast.makeText(this, "", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Please connect internet", Toast.LENGTH_SHORT).show()
            notConnectAlert()
        }

    }

    private fun notConnectAlert() {
        val builder = android.app.AlertDialog.Builder(this, R.style.CustomAlertDialog).create()
        val view = layoutInflater.inflate(R.layout.alert_not_connection,null)
        builder.setView(view)
        builder.setCanceledOnTouchOutside(false)
        builder.show()

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

        // algorithm click listeners
        binding.mmItem5.setOnClickListener {
            comingSoonAlert()
        }



    }

    private fun comingSoonAlert() {
        val alertComingSoon: AlertDialog
        val builder = AlertDialog.Builder(this)
        val inflater = LayoutInflater.from(this)
        val dialogView = inflater.inflate(R.layout.coming_soon_alert_, null)

        val width = WindowManager.LayoutParams.MATCH_PARENT // Specify a fixed width in pixels
        val height = 300 // Specify a fixed height in pixels
        dialogView.minimumWidth = width
        dialogView.minimumHeight = height

        builder.setView(dialogView)
        alertComingSoon = builder.create()
        alertComingSoon.show()

    }

    companion object {
        private fun checkForInternet(context: Context): Boolean {

            // register activity with the connectivity manager service
            val connectivityManager = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager

            // if the android version is equal to M
            // or greater we need to use the
            // NetworkCapabilities to check what type of
            // network has the internet connection
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {

                // Returns a Network object corresponding to
                // the currently active default data network.
                val network = connectivityManager.activeNetwork ?: return false

                // Representation of the capabilities of an active network.
                val activeNetwork = connectivityManager.getNetworkCapabilities(network) ?: return false

                return when {
                    // Indicates this network uses a Wi-Fi transport,
                    // or WiFi has network connectivity
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_WIFI) -> true

                    // Indicates this network uses a Cellular transport. or
                    // Cellular has network connectivity
                    activeNetwork.hasTransport(NetworkCapabilities.TRANSPORT_CELLULAR) -> true

                    // else return false
                    else -> false
                }
            } else {
                // if the android version is below M
                @Suppress("DEPRECATION") val networkInfo =
                    connectivityManager.activeNetworkInfo ?: return false
                @Suppress("DEPRECATION")
                return networkInfo.isConnected
            }
        }
    }

}