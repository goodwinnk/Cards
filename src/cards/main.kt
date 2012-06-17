package cards

import cards.CardValue as V
import cards.Suit as S
import java.util.Set

fun spade(value : CardValue) : Card = Card(Suit.Spades, value)
fun club(value : CardValue) : Card = Card(Suit.Clubs, value)
fun diamond(value : CardValue) : Card = Card(Suit.Diamonds, value)
fun heart(value : CardValue) : Card = Card(Suit.Hearts, value)

class StateDecision(val tricksByMain : Int, val tricksByWhistBidders : Int, val bestMoves : Set<Card>)

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

//    val roundState = cards.RoundState(spade(V.Ace), PlayerId.Main)
//    roundState.addCard(heart(V.N7))
//    roundState.addCard(spade(V.N10))
//
//    println(roundState.trickBy(Suit.Hearts))
//    println(shift(PlayerId.Main, 1))
//    println(shift(PlayerId.Main, 2))

//    val sp = array(true, false, false, false, true, false, false, false)
//    val cl = array(false, true, false, false, false, true, false, false)
//    val d = array(false, false, true, false, false, false, true, false)
//    val h = array(false, false, false, true, false, false, false, true)
//    val playerState = PlayerState(array(sp, cl, d, h))
//    println(playerState)

//    println("Moves: ${OrdinalGameSolver.possibleMovesForFirstInRound(playerState)}")
//    println("In round moves: ${OrdinalGameSolver.possibleMovesForPlayer(
//            playerState, Card(Suit.Diamonds, V.N10), Suit.Hearts)}")
}