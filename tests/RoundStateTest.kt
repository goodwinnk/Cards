package cards.tests

import cards.*
import cards.CardValue as V

class RoundStateTest {
    test fun withTrumpByLeft() {
        val roundState = cards.RoundState(spade(V.Ace), PlayerId.Main)
        roundState.addCard(heart(V.N7)) // trick
        roundState.addCard(spade(V.N10))
    }
}