package cards

public enum class CardValue(val number : Int) {
    N7 : CardValue(0)
    N8 : CardValue(1)
    N9 : CardValue(2)
    N10 : CardValue(3)
    Jack : CardValue(4)
    Queen : CardValue(5)
    King : CardValue(6)
    Ace : CardValue(7)

    public fun toString() : String =
        when (this) {
            N7 -> "7"
            N8 -> "8"
            N9 -> "9"
            N10 -> "10"
            Jack -> "J"
            Queen -> "Q"
            King -> "K"
            Ace -> "A"
        }
}