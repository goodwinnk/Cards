package cards

import java.util.HashMap

fun <T>init(initFun : () -> T) : T = initFun()

fun card(suit : Suit, value: CardValue) : Card {
    return deck.get(suit)!!.get(value)!!
}

// TODO: Make constructor private
public class Card (public val suit : Suit, public val value : CardValue) {
    public fun toString() : String {
        return value.toString() + suit.toString()
    }
}

private val deck = init {
    val deckInit = HashMap<Suit, HashMap<CardValue, Card>>()
    for (suit : Suit in Suit.values()) {
        deckInit.put(suit, HashMap<CardValue, Card>())
        for (value : CardValue in CardValue.values()) {
            deckInit.get(suit)!!.put(value, Card(suit, value))
        }
    }

    deckInit
}
