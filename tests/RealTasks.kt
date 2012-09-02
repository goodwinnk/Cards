package cards.tests

import cards.CardValue.*
import cards.Hand
import cards.Suit.*
import cards.card
import junit.framework.Assert
import org.junit.Test
import cards.initHand
import java.util.Arrays
import cards.initNormalGameState
import cards.initRoundState

public class RealTasks {
    Test public fun task1() {
        val firstHand = initHand(
                card(Spades, Ace),
                card(Spades, King),
                card(Spades, Jack),
                card(Spades, Eight),
                card(Clubs, King),
                card(Clubs, Jack),
                card(Clubs, Eight),
                card(Clubs, Seven),
                card(Hearts, King),
                card(Hearts, Queen))

        val secondHand = initHand(
                card(Spades, Queen),
                card(Spades, Ten),
                card(Spades, Seven),
                card(Clubs, Ace),
                card(Clubs, Queen),
                card(Diamonds, King),
                card(Diamonds, Queen),
                card(Diamonds, Nine),
                card(Diamonds, Eight),
                card(Hearts, Nine))

        val thirdHand = initHand(
                card(Spades, Nine),
                card(Clubs, Ten),
                card(Clubs, Nine),
                card(Diamonds, Ace),
                card(Diamonds, Ten),
                card(Diamonds, Seven),
                card(Hearts, King),
                card(Hearts, Jack),
                card(Hearts, Ten),
                card(Hearts, Eight))

        val hands = Arrays.asList(firstHand, secondHand, thirdHand)

        val normalGameState = initNormalGameState(hands, initRoundState(), 2)
        Assert.assertEquals(5, normalGameState.numOfTricksWithTrump(Clubs, 0))
    }
}