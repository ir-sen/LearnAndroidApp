package kan.kis.learnAndroidApp.Presentation.Adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.recyclerview.widget.ListAdapter
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem
import kan.kis.learnAndroidApp.R


class CardAdapter: ListAdapter<CardItem, CardViewHolder>(CardDiffCallBack()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val layout = R.layout.item_header
        val view = LayoutInflater.from(parent.context).inflate(
            layout,
            parent,
            false
        )
        return CardViewHolder(view)
    }



    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val itemThis = getItem(position)
        holder.itemView.animation = AnimationUtils.loadAnimation(holder.itemView.context,
        R.anim.recycle_view_one)
        holder.title.text = itemThis.title
        holder.bodyText.text = itemThis.inside
    }


}