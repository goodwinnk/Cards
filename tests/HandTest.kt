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
        Assert.assertEquals("Hand: K♣ 7♠ ", initHand(arrayList(card(Clubs, King), card(Spades, Seven))).toString())
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