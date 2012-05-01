import java.util.ArrayList
import CardValue as V
import Suit as S
import javax.smartcardio.Card

class Card(val suit : Suit, val value : CardValue) {
    public fun toString() : String {
        return value.toString() + suit.toString()
    }
}

fun spade(value : CardValue) : Card = Card(Suit.Spades, value)
fun club(value : CardValue) : Card = Card(Suit.Clubs, value)
fun diamond(value : CardValue) : Card = Card(Suit.Diamonds, value)
fun heart(value: CardValue) : Card = Card(Suit.Hearts, value)

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

private fun createCards(suit : Suit, values : Array<CardValue>) : Array<Card> = Array<Card>(values.size) { Card(suit, values[it]) }

fun spades(vararg values : CardValue) : Array<Card> = createCards(Suit.Spades, values)
fun clubs(vararg values : CardValue) : Array<Card> = createCards(Suit.Clubs, values)
fun diamonds(vararg values : CardValue) : Array<Card> = createCards(Suit.Diamonds, values)
fun hearts(vararg values: CardValue) : Array<Card> = createCards(Suit.Hearts, values)

fun main(args : Array<String>) {
    println(Player(
            spades(V.N7, V.N10, V.King),
            clubs(V.Queen, V.King, V.Ace),
            diamonds(V.N7, V.Jack, V.Queen, V.Ace),
            hearts()))
}