package cards

public enum class CardValue(val number : Int) {
    Seven : CardValue(0)
    Eight : CardValue(1)
    Nine : CardValue(2)
    Ten : CardValue(3)
    Jack : CardValue(4)
    Queen : CardValue(5)
    King : CardValue(6)
    Ace : CardValue(7)

    public fun toString() : String =
        when (this) {
            Seven -> "7"
            Eight -> "8"
            Nine -> "9"
            Ten -> "10"
            Jack -> "J"
            Queen -> "Q"
            King -> "K"
            Ace -> "A"
        }
}