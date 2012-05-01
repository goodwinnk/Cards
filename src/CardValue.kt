public enum class CardValue {
    N6
    N7
    N8
    N9
    N10
    Jack
    Queen
    King
    Ace

    public fun toString() : String {
        when (this) {
            N6 -> return "6"
            N7 -> return "7"
            N8 -> return "8"
            N9 -> return "9"
            N10 -> return "10"
            Jack -> return "J"
            Queen -> return "Q"
            King -> return "K"
            Ace -> return "A"
        }

        return ""
    }
}