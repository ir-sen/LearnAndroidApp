package kan.kis.learnAndroidApp.Presentation.frgments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import kan.kis.learnAndroidApp.Presentation.CardLearnActivity
import kan.kis.learnAndroidApp.Presentation.frgments.ActivQuestionFragment.Companion.EXTRA_ACTIV_QUESTION
import kan.kis.learnAndroidApp.Presentation.frgments.FragmentQuestionFragment.Companion.EXTRA_FRAG_QUESTION
import kan.kis.learnAndroidApp.R

class ActivFragMenuFragment: Fragment() {


    lateinit var activityQuestion: TextView
    lateinit var fragmentQuestion: TextView

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activ_frag_menu_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initTools()
        initListeners()
        Toast.makeText(requireContext(), "This is require menu", Toast.LENGTH_SHORT).show()
    }


    private fun initListeners() {
        activityQuestion.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(requireContext(), EXTRA_ACTIV_QUESTION))
        }
        fragmentQuestion.setOnClickListener {
            startActivity(CardLearnActivity().newIntent(requireContext(), EXTRA_FRAG_QUESTION))
        }


    }

    private fun initTools() {
        if (activity != null) {
            activityQuestion = requireActivity().findViewById(R.id.activityQuestionTv)
            fragmentQuestion = requireActivity().findViewById(R.id.fragmentQuestionTv)


        }
    }


    companion object {
        val EXTRA_ACT_FG_M_KEY = "ActivFragMenuFragmentKey"
    }
}