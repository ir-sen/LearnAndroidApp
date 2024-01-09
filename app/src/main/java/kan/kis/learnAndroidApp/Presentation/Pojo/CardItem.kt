package kan.kis.learnAndroidApp.Presentation.Pojo

data class CardItem(
    val id: Int,
    val type: TypeItem,
    val inside: String,
    val title: String
): java.io.Serializable


enum class TypeItem {
    HEADER, BODY
}