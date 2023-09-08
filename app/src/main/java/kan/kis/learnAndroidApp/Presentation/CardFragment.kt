package kan.kis.learnAndroidApp.Presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import kan.kis.learnAndroidApp.R

class CardFragment: Fragment() {

    lateinit var textMain: TextView



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cart_fragment, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textMain = requireActivity().findViewById(R.id.textView)
//        btn = requireActivity().findViewById(R.id.btn_test)

//        btn.setOnClickListener {
////            textMain.text = "Second Fragment"
////            Log.d("lsdkjf", "Open this Fragment")
//            openFragment(ThreadsCardFragment())
//        }
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