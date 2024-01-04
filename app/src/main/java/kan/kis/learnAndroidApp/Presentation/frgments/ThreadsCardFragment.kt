package kan.kis.learnAndroidApp.Presentation.frgments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kan.kis.learnAndroidApp.R

class ThreadsCardFragment: Fragment() {


//    lateinit var textMain: TextView
//
//    lateinit var btn: Button

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.kotlin_basic_fragmen, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        textMain = requireActivity().findViewById(R.id.textView)
//        btn = requireActivity().findViewById(R.id.btn_test)
//
//        btn.setOnClickListener {
//            textMain.text = "Second Fragment"
//            Log.d("lsdkjf", "Open this Fragment")
//        }


    }


    companion object {
        val EXTRA_KEY_2_CART = "ThreadsCardFragmentExtra"
    }

}