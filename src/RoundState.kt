package cards

import java.util.List

class RoundState(val firstCard : Card, val moveBy : PlayerId) {
    val cards : Array<Card?> = Array<Card?>(3, { if (it == 0) firstCard else null })
    var secondCard : Card?
        get() = cards[1]
        set(value) = cards[1] = value
    var thirdCard : Card?
        get() = cards[2]
        set(value) = cards[2] = value

    fun addCard(card : Card) {
        if (secondCard == null) {
            secondCard = card
        } else if (thirdCard == null) {
            thirdCard = card
        } else {
            throw IllegalStateException("Can't add card to finished round")
        }
    }

    fun isFinished() : Boolean = thirdCard != null

    fun trickBy(trump : Suit) : PlayerId {
        val all = cards.filterNotNull()
        if (all.size != cards.size) {
            throw IllegalStateException("Round isn't finished")
        }

        if (all.any({it.suit == trump})) {
            return moveBy
            // return moveBy.shift(bustIndexWithSuit(trump, all))
        }
        return moveBy
        // return moveBy.shift(bustIndexWithSuit(firstCard.suit, all))
    }

    private fun bustIndexWithSuit(suit : Suit, cards : List<Card>) : Int {
        var bestValue = -1
        var bestIndex = -1
        var curIndex = 0
        while (curIndex < cards.size) {
            if (cards[curIndex].suit == suit) {
                if (cards[curIndex].value.number > bestValue) {
                    bestValue = cards[curIndex].value.number
                    bestIndex = curIndex
                }
            }

            curIndex++
        }

        println(bestIndex)

        return bestIndex
    }
}