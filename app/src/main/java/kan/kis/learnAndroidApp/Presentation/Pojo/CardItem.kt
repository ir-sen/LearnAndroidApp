package kan.kis.learnAndroidApp.Presentation.Pojo

data class CardItem(
    val id: Int,
    val type: TypeItem,
    val inside: String
)


enum class TypeItem {
    HEADER, BODY
}