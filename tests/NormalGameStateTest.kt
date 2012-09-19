package cards.tests

import cards.CardValue.*
import cards.Hand
import cards.Suit.*
import org.junit.Before
import cards.card
import cards.initHand
import org.junit.Test
import org.junit.Assert
import cards.RoundState
import cards.NormalGameState
import java.util.Arrays
import cards.initRoundState
import cards.Card
import cards.initNormalGameState
import cards.getPossibleMoves

public class NormalGameStateTest() {
    private var hand : Hand? = null

    Before public fun setUp() {
        hand = initHand(card(Spades, Ace), card(Spades, King), card(Spades, Seven), card(Clubs, King), card(Clubs, Queen))
    }

    Test public fun testGuaranteedTricks() {
        val firstHand = initHand(card(Spades, Ace), card(Clubs, Ace), card(Diamonds, Ace))
        val secondHand = initHand(card(Spades, King), card(Clubs, King), card(Diamonds, King))
        val thirdHand = initHand(card(Spades, Queen), card(Clubs, Queen), card(Diamonds, Queen))

        val hands  = array(firstHand, secondHand, thirdHand).toList()

        val tricks : Int = initNormalGameState(hands, initRoundState(), 0).numOfTricksWithTrump(null, 0)
        Assert.assertEquals(3, tricks)
    }

    Test public fun testNumberTricks() : Unit {
        val firstHand = initHand(card(Spades, Seven), card(Hearts, Ace), card(Hearts, King))
        val secondHand = initHand(card(Spades, King), card(Clubs, King), card(Diamonds, King))
        val thirdHand = initHand(card(Spades, Queen), card(Clubs, Queen), card(Diamonds, Queen))

        val hands = Arrays.asList(firstHand, secondHand, thirdHand)

        Assert.assertEquals(2, initNormalGameState(hands, initRoundState(), 0).numOfTricksWithTrump(null, 0))
        Assert.assertEquals(0, initNormalGameState(hands, initRoundState(), 2).numOfTricksWithTrump(null, 0))
    }

    Test public fun testPossibleMovesNoSuitNoTrump() {
        val roundState = initRoundState().addCard(card(Diamonds, Queen)).addCard(card(Clubs, Seven))
        val possibleMoves = getPossibleMoves(hand!!, roundState, Hearts)

        val expectedCards = hand!!.cards.toArray()
        val actualCards = possibleMoves.toArray()

        Assert.assertArrayEquals(expectedCards, actualCards)
    }

    Test public fun testPossibleMovesWithTrump() {
        val roundState = initRoundState().addCard(card(Diamonds, Queen)).addCard(card(Clubs, Seven))
        val possibleMoves = getPossibleMoves(hand!!, roundState, Clubs)

        val expectedCards = array(card(Clubs, Queen), card(Clubs, King))
        val actualCards = possibleMoves.toArray()

        Assert.assertArrayEquals(expectedCards, actualCards)
    }

    Test public fun testPossibleMovesWithSameSuit() {
        val roundState = initRoundState().addCard(card(Spades, Queen)).addCard(card(Clubs, Seven))
        val possibleMoves = getPossibleMoves(hand!!, roundState, Clubs)

        val expectedCards = array(card(Spades, Seven), card(Spades, King), card(Spades, Ace))
        val actualCards = possibleMoves.toArray()

        Assert.assertArrayEquals(expectedCards, actualCards)
    }

    Test public fun testPossibleMovesWithEmptyRound() {
        val possibleMoves = getPossibleMoves(hand!!, initRoundState(), Spades)

        val expectedCards = hand!!.cards.toArray()
        val actualCards = possibleMoves.toArray()

        Assert.assertArrayEquals(expectedCards, actualCards)
    }
}
