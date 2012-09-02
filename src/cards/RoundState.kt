package cards

import java.util.List
import java.util.ArrayList
import java.util.Collection
import java.util.Collections

fun initRoundState() : RoundState = RoundState(Collections.emptyList<Card>()!!, null)

public class RoundState(val cards : List<Card>, public val firstMoveSuit : Suit?) {
    public fun addCard(card : Card) : RoundState {
        if (isFinished) {
            throw IllegalStateException("Round is already finished")
        }

        val newCards = ArrayList<Card>(cards)
        newCards.add(card)
        return RoundState(newCards, if (isEmpty) { card.suit } else { firstMoveSuit })
    }

    public val isEmpty : Boolean get() = cards.isEmpty()
    public val isFinished : Boolean get() = cards.size() == 3

    public fun indexOfWinner(trump : Suit?) : Int {
        if (!isFinished) {
            throw IllegalStateException("Invalid state of round for getting winner")
        }

        var winnerIndex : Int = 0
        for (i in 0..cards.size() - 1) {
            if ((compare(cards.get(winnerIndex), cards.get(i), trump)).sure() < 0) {
                winnerIndex = i
            }

        }
        return winnerIndex
    }

    private var hashCodeCache : Int = 0
    private var hashCodeReady : Boolean = false

    public fun hashCode() : Int {
        if (!hashCodeReady) {
            hashCodeCache = 17
            for (card in cards) {
                hashCodeCache = 31 * hashCodeCache + card.javaClass.hashCode()
            }
            hashCodeReady = true
        }

        return hashCodeCache
    }

    public fun equals(obj : Any?) : Boolean {
        if (obj == null || !(obj is RoundState)) {
            return false
        }

        val otherRoundState = (obj as RoundState)
        if (cards.size() != otherRoundState.cards.size()) {
            return false
        }

        for (i in 0..cards.size() - 1) {
            if (cards.get(i) != otherRoundState.cards.get(i)) {
                return false
            }
        }

        return true
    }

    class object {
        public open fun compare(first : Card, second : Card, trumpSuit : Suit?) : Int {
            if (first.suit == second.suit) {
                return first.value.number - second.value.number
            } else if (second.suit == trumpSuit) {
                return -1
            } else {
                return 1
            }
        }
    }
}
