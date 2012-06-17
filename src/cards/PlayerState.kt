package cards

class PlayerState(val suitSets : Array<Array<Boolean>>) {
    fun suitIndex(suit : Suit) : Int = when (suit) {
        Suit.Spades -> 0
        Suit.Clubs -> 1
        Suit.Diamonds -> 2
        Suit.Hearts -> 3
    }

    fun suitSet(suit : Suit) = suitSets[suitIndex(suit)]

    /**
     * Get state different from the current one to one card.
     */
    fun makeMove(card : Card) : PlayerState {
        val index = suitIndex(card.suit)
        val suitPartCopy : Array<Boolean> = suitSets[index].copyOf(suitSets[index].size)

        if (!suitPartCopy[card.value.number]) {
            throw IllegalStateException()
        }

        suitPartCopy[card.value.number] = false

        val newState : Array<Array<Boolean>> = suitSets.copyOf(suitSets.size)
        newState[index] = suitPartCopy
        return PlayerState(newState)
    }

    public fun toString() : String {
        val builder = StringBuilder()
        fun printSuit(suitCards : Array<Boolean>, suit : Suit) {
            suitCards.indices.forEach {
                if (suitCards[it]) {
                    builder.append(Card(suit, cardValue(it)).toString())
                }
            }
        }

        printSuit(suitSets[suitIndex(Suit.Spades)], Suit.Spades)
        printSuit(suitSets[suitIndex(Suit.Clubs)], Suit.Clubs)
        printSuit(suitSets[suitIndex(Suit.Diamonds)], Suit.Diamonds)
        printSuit(suitSets[suitIndex(Suit.Hearts)], Suit.Hearts)

        return builder.toString()!!
    }

    public fun hashCode() : Int {
        return 0
    }
}
