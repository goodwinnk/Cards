package cards

import java.util.List
import java.util.ArrayList
import java.util.Collection
import java.util.Comparator

private fun <T>initArrayList(size : Int, initFun : (Int) -> T) : ArrayList<T> {
    val arrayList = ArrayList<T>(size)
    for (i in 0..size - 1) {
        arrayList.add(initFun(i))
    }

    return arrayList
}

public fun initHand(vararg cards : Card) : Hand {
    return initHand(cards.toList())
}

// Move to class object
public fun initHand(cards: List<Card>) : Hand {
    // Sort cards
    val clonedCards = cards.sort(object : Comparator<Card> {
        public override fun equals(obj: Any?): Boolean = this.equals(obj)
        public override fun compare(o1: Card?, o2: Card?): Int {
            return if (o1 == null) {
                if (o2 == null) return 0 else Integer.MIN_VALUE
            } else {
                if (o2 == null) return Integer.MAX_VALUE else compareCards(o1, o2)
            }
        }

        fun compareCards(card1: Card, card2: Card): Int {
            if (card1.suit.index == card2.suit.index) {
                return card1.value.number - card2.value.number
            }

            return card1.suit.index - card2.suit.index
        }
    })

    // Put suits to different lists
    val suitCards = initArrayList(Suit.values().size, { ArrayList<Card>() })
    for (card in clonedCards) {
        suitCards[card.suit.index].add(card)
    }

    return Hand(clonedCards, suitCards)
}

public class Hand public(val cards: List<Card>, val suitCards: List<out List<Card>> ) {
    public fun removeCard(card: Card): Hand {
        val newCards = ArrayList(cards)
        if (!newCards.remove(card)) {
            throw IllegalAccessError("Hand doesn't contain card")
        }

        val newSuitCards = ArrayList<List<Card>>(suitCards)
        val cardCollection = ArrayList<Card>(getSuitCards(card.suit))

        // Refresh collection with removing suit card
        if (!cardCollection.remove(card)) {
            throw IllegalAccessError("Suit cards doesn't contain card")
        }
        newSuitCards.set(card.suit.index, cardCollection)

        return Hand(newCards, newSuitCards)
    }

    public fun getSuitCards(suit: Suit): Collection<Card> = suitCards.get(suit.index)
    public fun isEmpty(): Boolean = cards.isEmpty()

    private var hashCodeCache: Int = 0
    private var hashCodeReady: Boolean = false

    public fun hashCode(): Int {
        if (!hashCodeReady) {
            hashCodeCache = 17
            for (card : Card in cards) {
                hashCodeCache = 31 * hashCodeCache + (card as Object).hashCode()
            }
            hashCodeReady = true
        }

        return hashCodeCache
    }

    public fun equals(obj: Any?): Boolean {
        if (!(obj is Hand)) {
            return false
        }

        val otherHand = (obj as Hand)
        if (otherHand.cards.size() != cards.size()) {
            return true
        }

        for (i in 0..cards.size() - 1) {
            if (cards.get(i) != otherHand.cards.get(i)) {
                return false
            }
        }

        return true
    }

    public var toStringCache: String? = null
    public fun toString(): String {
        if (toStringCache == null) {
            val builder: StringBuilder = StringBuilder("Hand: ")
            for (card : Card? in cards) {
                builder.append(card)?.append(" ")
            }

            toStringCache = builder.toString()
        }

        return toStringCache!!
    }
}
