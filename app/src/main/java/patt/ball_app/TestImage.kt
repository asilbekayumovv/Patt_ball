package patt.ball_app

data class TestImage(
    val question: String,
    val description: String,
    val variantA: Int,
    val variantB: Int,
    val variantC: Int,
    val variantD: Int,
    val answer: Int,

) {
    companion object {
        fun add(function: () -> Unit) {

        }
    }
}
