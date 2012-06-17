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

    public fun toString() : String {
        when (this) {
            N7 -> return "7"
            N8 -> return "8"
            N9 -> return "9"
            N10 -> return "10"
            Jack -> return "J"
            Queen ->return "Q"
            King -> return "K"
            Ace -> return "A"
            else -> "Error" + this.number
        }

        return "Error"
    }
}

// TODO: Error can't initialize enum value with constructor
fun cardValue(number : Int) = when (number) {
    0 -> CardValue.N7
    1 -> CardValue.N8
    2 -> CardValue.N9
    3 -> CardValue.N10
    4 -> CardValue.Jack
    5 -> CardValue.Queen
    6 -> CardValue.King
    7 -> CardValue.Ace
    else -> throw IllegalStateException()
}