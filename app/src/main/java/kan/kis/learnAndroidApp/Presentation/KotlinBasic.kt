package kan.kis.learnAndroidApp.Presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.ValueEventListener
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import kan.kis.learnAndroidApp.Presentation.Adapters.CardDiffCallBack
import kan.kis.learnAndroidApp.Presentation.Adapters.CardViewHolder
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.Presentation.Pojo.TypeItem
import kan.kis.learnAndroidApp.R

class KotlinBasic: Fragment() {

    lateinit var textMain: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterThis: ListAdapter<CardItem, CardViewHolder>


    private lateinit var database: DatabaseReference


    private val TAG = "KotlinBasicFragmentTAG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //create and add recycle view to fragment
        val view = inflater.inflate(R.layout.cart_fragment, container, false)
        recyclerView = view.findViewById(R.id.info_rv)
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        
        adapterThis = object : ListAdapter<CardItem, CardViewHolder>(CardDiffCallBack()) {
            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                return CardViewHolder(itemView)

            }

            override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
                val itemThis = getItem(position)
                // recycle item listener
                holder.itemView.setOnClickListener {
                    Log.d(TAG, "this is clicked item ${itemThis.title}")
                }
                // add animation to recycle view
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
                    R.anim.recycle_view_one)
                holder.title.text = itemThis.title
                holder.bodyText.text = itemThis.inside

            }
        }
        recyclerView.adapter = adapterThis
        // заглушка
        val listRv = mutableListOf<CardItem>(CardItem(12, TypeItem.HEADER,
            "Kotlin is a statically typed, cross-platform," +
                    " modern programming language that was developed by JetBrains," +
                    " the company known for creating popular integrated development environments (IDEs)" +
                    " like IntelliJ IDEA. Kotlin was officially announced as a new language " +
                    "for the Java Virtual Machine (JVM) in 2011 and later made open source." +
                    " Since then, it has gained significant popularity in the software development" +
                    " community, especially in Android app development," +
                    " but it can also be used for server-side development, web applications, and more.", "Kotlin"))
//        listRv.add(CardItem(1, TypeItem.BODY, "jsdof", "jslidf"))
//        listRv.add(CardItem(2, TypeItem.HEADER, "jsdof", ""))
//        listRv.add(CardItem(3, TypeItem.BODY, "jsdof", "jslidf"))
//        listRv.add(CardItem(4, TypeItem.BODY, "jsdof", "jslidf"))
//        listRv.add(CardItem(5, TypeItem.BODY, "jsdof", "jslidf"))

        checkFirebaseDataBase(listRv)
        adapterThis.submitList(listRv)
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textMain = requireActivity().findViewById(R.id.textView)
    }
    // return converting json from firebase
    private fun checkFirebaseDataBase(mutableList: MutableList<CardItem>): MutableList<CardItem> {
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
                        mutableList.add(
                            CardItem(
                                it.child("id").value.toString().toInt(),
                                TypeItem.HEADER,
                                it.child("body").value.toString(),
                                it.child("title").value.toString()
                            )
                        )
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
        return mutableList
    }

    private fun openFragment(fragment: Fragment) {
        val transactionFragment = requireActivity().supportFragmentManager.beginTransaction()
        transactionFragment.replace(R.id.fragment_card_container, fragment)
        transactionFragment.addToBackStack(null)
        transactionFragment.commit()
    }

    companion object {
        val EXTRA_KEY_FIRST_CART = "CardFragmentKeyExtra"
    }
}