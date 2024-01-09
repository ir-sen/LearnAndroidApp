package kan.kis.learnAndroidApp.Presentation.frgments

import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.Presentation.frgments.KotlinBasicFragment.Companion.KEY_ARGS
import kan.kis.learnAndroidApp.R


class FullExpFragment: Fragment() {

    lateinit var bodyInfView: TextView
    lateinit var titleView: TextView
    lateinit var copyView: View

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.full_exp_fragment, container, false)

        return view
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initViews()
        initArguments()

        clickListeners()

    }

    private fun clickListeners() {

        copyView.setOnClickListener {
            copyFunction()
        }
    }

    private fun initViews() {
        bodyInfView = requireView().findViewById<TextView>(R.id.insideInfoTv)
        titleView = requireView().findViewById<TextView>(R.id.titleFEF)
        copyView = requireView().findViewById(R.id.copyView)
    }



    // add to copy buffer
    private fun copyFunction() {
        val cm: ClipboardManager =
            requireActivity()!!.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cm.setText(bodyInfView.text)
        Toast.makeText(context, "Copied to clipboard", Toast.LENGTH_SHORT).show()
    }

    // get CardItem (argument from fragment)
    private fun initArguments() {
        val bundle = arguments
        if (bundle != null) {
            val myData = bundle.getSerializable(KEY_ARGS) as CardItem

            bodyInfView.text = myData.inside
            titleView.text = myData.title

        }
    }

}