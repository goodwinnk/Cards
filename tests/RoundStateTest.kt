package cards.tests

import cards.CardValue.*
import cards.Suit.*
import cards.card
import cards.initRoundState
import org.junit.Assert
import org.junit.Test

public class RoundStateTest {
    Test fun firstCardSuit() {
        val roundState = initRoundState().addCard(card(Diamonds, Ace)).addCard(card(Clubs, Ace))
        Assert.assertEquals(Diamonds, roundState.firstMoveSuit)
    }

    Test fun firstTakeWithTrump1() {
        val roundState = initRoundState().addCard(card(Spades, Ace)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven))

        // A♠, Q♠, 7♣  + ♣ -> 7♣
        Assert.assertEquals(2, roundState.indexOfWinner(Clubs))
    }

    Test fun firstTakeWithTrump2() {
        val roundState = initRoundState().addCard(card(Spades, Ace)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven))

        // A♠, Q♠, 7♣  + ♥ -> A♠
        Assert.assertEquals(0, roundState.indexOfWinner(Hearts))
    }

    Test fun firstTakeWithTrump3() {
        val roundState = initRoundState().addCard(card(Spades, Jack)).addCard(card(Spades, Queen)).addCard(card(Clubs, Seven))

        // J♠, Q♠, 7♣ + ♥ -> Q♠
        Assert.assertEquals(1, roundState.indexOfWinner(Hearts))
    }
}
