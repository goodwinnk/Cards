package cards

import java.util.ArrayList
import cards.CardValue as V
import cards.Suit as S
import javax.smartcardio.Card
import java.util.Set
import java.util.Map
import java.util.HashSet
import java.util.HashMap
import java.util.Arrays
import java.util.List

class Card(val suit : Suit, val value : CardValue) {
    public fun toString() : String {
        return value.toString() + suit.toString()
    }
}

fun spade(value : CardValue) : Card = Card(Suit.Spades, value)
fun club(value : CardValue) : Card = Card(Suit.Clubs, value)
fun diamond(value : CardValue) : Card = Card(Suit.Diamonds, value)
fun heart(value : CardValue) : Card = Card(Suit.Hearts, value)

class Player(spades : Array<Card>, clubs : Array<Card>, diamonds : Array<Card>, hearts : Array<Card>) {
    private val spades = spades
    private val clubs = clubs
    private val diamonds = diamonds
    private val hearts = hearts

    public fun toString() : String {
        fun printCards(cards : Array<Card>) : String {
            var result = ""
            for (card in cards) {
                result += card.toString() + " "
            }

            return result
        }

        return "P: " + printCards(spades) + printCards(clubs) + printCards(diamonds) + printCards(hearts)
    }
}

class PlayerState(val suitSets : Array<Array<Boolean>>) {
    fun suitIndex(suit : Suit) : Int = when (suit) {
        Suit.Spades -> 0
        Suit.Clubs -> 1
        Suit.Diamonds -> 2
        Suit.Hearts -> 3
    }

    fun makeMove(card : Card) : PlayerState {
        val index = suitIndex(card.suit)
        val suitPartCopy : Array<Boolean> = suitSets[index].copyOf(suitSets[index].size)

        if (!suitPartCopy[card.value.number]) {
            throw IllegalStateException()
        }

        suitPartCopy[card.value.number] = false

        val newState : Array<Array<Boolean>> = suitSets.copyOf(suitSets.size)
        newState[index] = suitPartCopy
        return PlayerState(newState)
    }

    public fun toString() : String {
        val builder = StringBuilder()
        fun printSuit(suitCards : Array<Boolean>, suit : Suit) {
            suitCards.indices.forEach {
                if (suitCards[it]) {
                    builder.append(Card(suit, cardValue(it)).toString())
                }
            }
        }

        printSuit(suitSets[suitIndex(Suit.Spades)], Suit.Spades)
        printSuit(suitSets[suitIndex(Suit.Clubs)], Suit.Clubs)
        printSuit(suitSets[suitIndex(Suit.Diamonds)], Suit.Diamonds)
        printSuit(suitSets[suitIndex(Suit.Hearts)], Suit.Hearts)

        return builder.toString()!!
    }

    public fun hashCode() : Int {
        return 0
    }
}

class StateDecision(val tricksByMain : Int, val tricksByWhistBidders : Int, val bestMoves : Set<Card>)

class GameState(val moveNumber : Int, val moveBy : PlayerId,
                val main : PlayerState, val left : PlayerState, val right : PlayerState) {
    val activePlayerState = when (moveBy) {
        PlayerId.Main -> main
        PlayerId.Left -> left
        PlayerId.Right -> right
    }

    val isNewRound : Boolean = moveNumber % 3 == 0

    public fun hashCode() : Int {
        return 0
    }
}

class OrdinalGameSolver(initState : GameState, trump : Suit) {
    private var currentState = initState
    private var trump = trump

    // Move number -> State
    private val processedStates = Array(30, { HashSet<GameState>() })

    // Processed state -> Stored state analytics
    private val storedDecisions = HashMap<GameState, StateDecision>

    fun solve() : Int {

        val test = array(12, 13, 14)
        test.forEach {
            println(it)
        }
        return 0
    }

    fun doMove() {
        val activePlayerState = currentState.activePlayerState
        if (currentState.isNewRound) {

        }
    }

    class object {
        fun possibleMoveForFirstInRound(val activePlayerState : PlayerState) : Set<Card> {
            return HashSet<Card>()
        }
    }
}

private fun createCards(suit : Suit, values : Array<CardValue>) : Array<Card> = Array<Card>(values.size) { Card(suit, values[it]) }

fun spades(vararg values : CardValue) : Array<Card> = createCards(Suit.Spades, values)
fun clubs(vararg values : CardValue) : Array<Card> = createCards(Suit.Clubs, values)
fun diamonds(vararg values : CardValue) : Array<Card> = createCards(Suit.Diamonds, values)
fun hearts(vararg values : CardValue) : Array<Card> = createCards(Suit.Hearts, values)

fun main(args : Array<String>) {
    println(Player(
            spades(V.N7, V.N10, V.King),
            clubs(V.Queen, V.King, V.Ace),
            diamonds(V.N7, V.Jack, V.Queen, V.Ace),
            hearts()))

    val roundState = cards.RoundState(spade(V.Ace), PlayerId.Main)
    roundState.addCard(heart(V.N7))
    roundState.addCard(spade(V.N10))

    println(roundState.trickBy(Suit.Hearts))
    println(shift(PlayerId.Main, 1))
    println(shift(PlayerId.Main, 2))

    val sp = array(true, false, false, false, true, false, false, false)
    val cl = array(false, true, false, false, false, true, false, false)
    val d = array(false, false, true, false, false, false, true, false)
    val h = array(false, false, false, true, false, false, false, true)
    println(PlayerState(array(sp, cl, d, h)))

}