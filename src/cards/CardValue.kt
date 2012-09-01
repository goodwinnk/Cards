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

    public fun toString() : String {
        when (this) {
            Seven -> return "7"
            Eight -> return "8"
            Nine -> return "9"
            Ten -> return "10"
            Jack -> return "J"
            Queen ->return "Q"
            King -> return "K"
            Ace -> return "A"
        }

        throw IllegalStateException("Unknown card value")
    }
}