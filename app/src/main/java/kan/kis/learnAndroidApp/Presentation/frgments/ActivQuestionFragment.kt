package kan.kis.learnAndroidApp.Presentation.frgments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kan.kis.learnAndroidApp.Presentation.Adapters.CardDiffCallBack
import kan.kis.learnAndroidApp.Presentation.Adapters.CardViewHolder
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.Presentation.viewModels.ActivityQuestionFragmentViewModel
import kan.kis.learnAndroidApp.R

class ActivQuestionFragment: Fragment() {

    lateinit var textMain: TextView

    private lateinit var recyclerView: RecyclerView
    private lateinit var adapterThis: ListAdapter<CardItem, CardViewHolder>


    lateinit var viewModel: ActivityQuestionFragmentViewModel

    lateinit var listRv: MutableList<CardItem>

    private val TAG = "KotlinBasicFragmentTAG"

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //create and add recycle view to fragment
        val view = inflater.inflate(R.layout.activtiy_question_framgnet, container, false)
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

                // add argument
                val argumentFragment = Bundle()
                argumentFragment.putSerializable(KEY_ARGS, itemThis)
                // recycle item listener
                holder.itemView.setOnClickListener {
                    Log.d(TAG, "this is clicked item ${itemThis.title}")
                    // this is open fragment
                    openFragment(FullExpFragment(), argumentFragment)
                }

                // add animation to recycle view
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
                    R.anim.recycle_view_one)
                holder.title.text = itemThis.title
                holder.bodyText.text = itemThis.inside

            }
        }
        recyclerView.adapter = adapterThis

        listRv = mutableListOf()

        // init viewModel
        viewModel = ViewModelProvider(this)[ActivityQuestionFragmentViewModel::class.java]
        viewModel.checkFirebaseDataBase(listRv)
        // listener firebase data
        viewModel.firebaseDataBase.observe(viewLifecycleOwner) {
            Log.d("Justlkjioej", "LifeCycle answer: $it")
            adapterThis.submitList(it)
        }

        Log.d(TAG, "lifecycle: onCreateView")
        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        textMain = requireActivity().findViewById(R.id.titleTv)
        Log.d(TAG, "lifecycle: onViewCreated")
    }

    // put argument and open fragment
    private fun openFragment(fragment: Fragment, args: Bundle) {
        val transactionFragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.arguments = args
        transactionFragment.replace(R.id.fragment_card_container, fragment)
        transactionFragment.addToBackStack("ActivQuestionFragment")
        transactionFragment.commit()
    }


    companion object {
        const val EXTRA_ACTIV_QUESTION = "ActivityQuestions"
        const val KEY_ARGS = "KeyArgs"
    }
}