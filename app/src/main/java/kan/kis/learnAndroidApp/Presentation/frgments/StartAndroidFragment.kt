package kan.kis.learnAndroidApp.Presentation.frgments

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kan.kis.learnAndroidApp.Presentation.Adapters.CardDiffCallBack
import kan.kis.learnAndroidApp.Presentation.Adapters.CardViewHolder
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.Presentation.frgments.KotlinBasicFragment.Companion.KEY_ARGS
import kan.kis.learnAndroidApp.Presentation.viewModels.StartAndroidViewModel
import kan.kis.learnAndroidApp.R

class StartAndroidFragment: Fragment() {


    lateinit var viewModel: StartAndroidViewModel

    private lateinit var infoRecycleView: RecyclerView
    private lateinit var adapterInfo: ListAdapter<CardItem, CardViewHolder>

    lateinit var listInfo: MutableList<CardItem>

    private val TAG = "StartAndroidFragmentTAG"

    @SuppressLint("MissingInflatedId")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_start_android, container, false)
        infoRecycleView = view.findViewById(R.id.infoAndroidRv)
        infoRecycleView.layoutManager = LinearLayoutManager(requireContext())


        adapterInfo = object : ListAdapter<CardItem, CardViewHolder>(CardDiffCallBack()) {

            override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
                val itemView = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_header, parent, false)
                return CardViewHolder(itemView)
            }

            override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
                val itemThis = getItem(position)

                val argumentFragment = Bundle()
                argumentFragment.putSerializable(KEY_ARGS, itemThis)

                holder.itemView.setOnClickListener {
                    openFragment(FullExpFragment(), argumentFragment)
                }
                // add animation to recycle view
                holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
                    R.anim.recycle_view_one)

                holder.title.text = itemThis.title
                holder.bodyText.text = itemThis.inside
            }
        }
        infoRecycleView.adapter = adapterInfo

        listInfo = mutableListOf()

        viewModel = ViewModelProvider(this)[StartAndroidViewModel::class.java]
        viewModel.checkFirebaseDataBase(listInfo)

        viewModel.fireBaseAndroidBasic.observe(viewLifecycleOwner) {
            adapterInfo.submitList(it)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }


    private fun openFragment(fragment: Fragment, args: Bundle) {
        val transactionFragment = requireActivity().supportFragmentManager.beginTransaction()
        fragment.arguments = args
        transactionFragment.replace(R.id.fragment_card_container, fragment)
        transactionFragment.addToBackStack("add2")
        transactionFragment.commit()
    }



    companion object {
        const val EXTRA_START_ANDROID = "extraStartAndroid"
    }

}