package test.simple

import test.UsefulTestCase
import cards.*
import cards.Suit as S
import cards.CardValue as V
import kotlin.test.assertEquals

class SimpleTests: UsefulTestCase() {
    fun testPlayerInitialization() {
        assertEquals("P: 7♠ 10♠ K♠ Q♣ K♣ A♣ 7♦ J♦ Q♦ A♦ ",
                Player(spades(V.N7, V.N10, V.King),
                        clubs(V.Queen, V.King, V.Ace),
                        diamonds(V.N7, V.Jack, V.Queen, V.Ace),
                        hearts()).toString())
    }
}