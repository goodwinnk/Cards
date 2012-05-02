import java.util.ArrayList
import CardValue as V
import Suit as S
import javax.smartcardio.Card
import java.util.Set
import java.util.Map
import java.util.HashSet
import java.util.HashMap
import java.util.Arrays

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

enum class PlayerId {
    Main
    Left
    Right
}

class PlayerState(val suitSets : Array<Array<Byte>>) {
    fun suitIndex(suit : Suit) : Int = when (suit) {
        Suit.Spades -> 0
        Suit.Clubs -> 1
        Suit.Diamonds -> 2
        Suit.Hearts -> 3
    }

    fun makeMove(card : Card) : PlayerState {
//        val suitSetCopy : Array<Byte> = Arrays.copyOf(suitSets[suitIndex(card.suit)], 8)
//        suitSetCopy.set(card.value.number, 0)
//
//        val suitSets = array(suitSets)
         return this
    }

    public fun hashCode() : Int {
        return 0
    }
}

class OneRound(val mainCard: Card, val secondCard : Card, val thirdCard : Card,
               val moveBy : PlayerId, val trickBy : PlayerId)

class StateDecision(val tricksByMain : Int, val tricksByWhistBidders : Int, val bestMoves : Set<Card>)

class GameState(val moveNumber : Int, val moveBy : PlayerId, val main : PlayerState, val left : PlayerState, val right : PlayerState) {
    val activePlayerState = when (moveBy) {
        PlayerId.Main -> main
        PlayerId.Left -> left
        PlayerId.Right -> right
    }

    val isFirstMove : Boolean = moveNumber % 3 == 0

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
        return 0
    }

    fun doMove() {
        val activePlayerState = currentState.activePlayerState
        if (currentState.isFirstMove) {

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
}