package cards

public enum class Suit(public val index : Int) {
    Spades : Suit(0)
    Clubs : Suit(1)
    Diamonds : Suit(2)
    Hearts : Suit(3)

    public fun toString() : String {
        when (this) {
            Spades -> return "♠"
            Clubs -> return "♣"
            Diamonds -> return "♦"
            Hearts -> return "♥"
        }

        throw IllegalStateException("Invalid suit")
    }
}