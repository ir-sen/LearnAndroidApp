package kan.kis.learnAndroidApp.Presentation.frgments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import kan.kis.learnAndroidApp.R

class ActivFragMenuFragment: Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.activ_frag_menu_fragment_layout, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Toast.makeText(requireContext(), "This is require menu", Toast.LENGTH_SHORT).show()
    }



    companion object {
        val EXTRA_ACT_FG_M_KEY = "ActivFragMenuFragmentKey"
    }
}