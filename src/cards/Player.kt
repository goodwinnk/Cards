package cards

/**
 * Information about player hand.
 */
class Player(spades : Array<Card>, clubs : Array<Card>, diamonds : Array<Card>, hearts : Array<Card>) {
    private val spades = spades
    private val clubs = clubs
    private val diamonds = diamonds
    private val hearts = hearts

    public fun toString() : String {
        fun printCards(cards : Array<Card>) : String {
            var result = ""
            for (card in cards) {
                result += card.toString() + " "
            }

            return result
        }

        return "P: " + printCards(spades) + printCards(clubs) + printCards(diamonds) + printCards(hearts)
    }
}