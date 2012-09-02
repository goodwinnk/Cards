package cards.tests

import cards.CardValue.*
import cards.Hand
import cards.Suit.*
import cards.card
import junit.framework.Assert
import org.junit.Test
import cards.initHand

public class HandTest() {
    Test fun handToString() {
        Assert.assertEquals("Hand: 7♠ K♣ ", initHand(arrayList(card(Clubs, King), card(Spades, Seven))).toString())
    }

    Test fun handToStringSort() {
        Assert.assertEquals("Hand: 10♠ J♠ 9♣ Q♣ 8♦ K♦ 7♥ A♥ ", initHand(arrayList(
                card(Hearts, Ace),
                card(Diamonds, King),
                card(Clubs, Queen),
                card(Spades, Jack),
                card(Spades, Ten),
                card(Clubs, Nine),
                card(Diamonds, Eight),
                card(Hearts, Seven)
        )).toString())
    }

    Test fun removeCard() {
        val hand = initHand(arrayList(card(Clubs, King), card(Spades, Seven)))
        val newHand = hand.removeCard(card(Clubs, King))
        Assert.assertEquals("Hand: 7♠ ", newHand.toString())
    }

    Test fun testEqual() {
        val hand = initHand(arrayList(card(Clubs, King), card(Spades, Seven), card(Hearts, Jack)))
        val newHand = hand.removeCard(card(Clubs, King))

        val otherHand = initHand(arrayList(card(Hearts, Jack), card(Spades, Seven)))
        Assert.assertEquals(otherHand, newHand)
    }
}