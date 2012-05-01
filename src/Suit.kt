enum class Suit {
    Spades
    Clubs
    Diamonds
    Hearts

    public fun toString() : String {
        when (this) {
            Spades -> return "♠"
            Clubs -> return "♣"
            Diamonds -> return "♦"
            Hearts -> return "♥"
        }

        return ""
    }
}