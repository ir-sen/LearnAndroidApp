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

class StartAndroidViewModel: ViewModel() {

    private val TAG = "StartAndroidViewModelTAG"

    private val _fireBaseAndroidBasic = MutableLiveData<MutableList<CardItem>>()
    val fireBaseAndroidBasic: LiveData<MutableList<CardItem>>
        get() = _fireBaseAndroidBasic

    private lateinit var dataBase: DatabaseReference


    fun checkFirebaseDataBase(mutableList: MutableList<CardItem>): MutableList<CardItem> {
        dataBase = Firebase.database.reference
        // just check all child in fire base
        val myRef = dataBase.child("androidBasic").get().addOnSuccessListener {
            Log.d(TAG, "on success: ${it.child("item2")}")
            Log.d(TAG, "on success: ${it}")
            Log.d("GetOnlyBasic", " $it")
            for (chl in it.children) {
                mutableList.add(
                    CardItem(
                        chl.child("id").value.toString().toInt(),
                        TypeItem.HEADER,
                        chl.child("body").value.toString(),
                        chl.child("title").value.toString()
                    )
                )
                _fireBaseAndroidBasic.value = mutableList

            }
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

        dataBase.addValueEventListener(valueEventListener)

        myRef.addOnCompleteListener {
            Log.d(TAG, "Complete listeners: $it")
        }


        return mutableList
    }


}