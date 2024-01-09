package kan.kis.learnAndroidApp.Presentation.Adapters

import androidx.recyclerview.widget.DiffUtil
import kan.kis.learnAndroidApp.Presentation.Pojo.CardItem

class CardDiffCallBack: DiffUtil.ItemCallback<CardItem>() {

    override fun areItemsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: CardItem, newItem: CardItem): Boolean {
        return oldItem == newItem
    }


}