package kan.kis.learnAndroidApp.Presentation.Adapters

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kan.kis.learnAndroidApp.R

class CardViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val title = view.findViewById<TextView>(R.id.header_title)
    val bodyText = view.findViewById<TextView>(R.id.body_tv)

}