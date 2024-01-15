package kan.kis.learnAndroidApp.Presentation.viewModels

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.Presentation.Pojo.TypeItem

class FragmentQuestionViewModel: ViewModel() {

    private val TAG = "KotlinBasicViewModelTAG"

    private val _firebaseDataBase = MutableLiveData<MutableList<CardItem>>()
    val firebaseDataBase: LiveData<MutableList<CardItem>>
        get() = _firebaseDataBase

    private lateinit var database: DatabaseReference

    fun checkFirebaseDataBase(mutableList: MutableList<CardItem>): MutableList<CardItem> {
        database = Firebase.database.reference
        // just check all child in fire base and add to list
        val myRef = database.child("frgmentQuestion").get().addOnSuccessListener {
            Log.d(TAG, "on success: ${it.child("item2")}")
            Log.d(TAG, "on success: ${it}")
            for (child in it.children) {
                mutableList.add(
                    CardItem(
                        child.child("id").value.toString().toInt(),
                        TypeItem.HEADER,
                        child.child("body").value.toString(),
                        child.child("title").value.toString(),
                    )
                )
                _firebaseDataBase.value = mutableList
            }
        }.addOnFailureListener {
            Log.d(TAG, "on onfailure: $it")
        }


        // just listener change in firebaseDataBase
        val valueEventListener = object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {

            }

            override fun onCancelled(error: DatabaseError) {
                Log.d(TAG, "Listeners: ${error}")
            }
        }

        database.addValueEventListener(valueEventListener)

        myRef.addOnCompleteListener {
            Log.d(TAG, "Complete listeners: $it")
        }


        return mutableList
    }
}