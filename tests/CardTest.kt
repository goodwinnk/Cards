package cards.tests

import junit.framework.Assert
import org.junit.Test
import cards.Suit
import cards.CardValue
import cards.card

public open class CardTest() {
    Test fun suitToString() {
        Assert.assertEquals("♠", Suit.Spades.toString())
    }

    Test fun cardStringTest() : Unit {
        Assert.assertEquals("J♦", card(Suit.Diamonds, CardValue.Jack).toString())
    }
}
