package cards

import java.util.List
import java.util.Map
import java.util.HashMap
import java.util.ArrayList
import sun.plugin.dom.exception.InvalidStateException

class RoundState() {
    private val moves = ArrayList<#(PlayerId, Card)>()
    private val playerToMove = HashMap<PlayerId, Card>()

    public fun addMove(playerId : PlayerId, card : Card) {
        moves.add(#(playerId, card))
        playerToMove.put(playerId, card)
    }

    public fun lastMove() : #(PlayerId, Card) {
        if (moves.size == 0) {
            throw InvalidStateException("There're no moves in round")
        }

        return moves.last()
    }

    public fun playerMove(playerId : PlayerId) : Card? {
        return playerToMove.get(playerId)
    }

    public fun isFinished() : Boolean {
        return playerToMove.containsKey(PlayerId.Left) &&
                playerToMove.containsKey(PlayerId.Right) &&
                playerToMove.containsKey(PlayerId.Main)
    }

//
//
//    public fun getAllPlayedCards() : Map<PlayerId, Card> {
//        val all = HashMap<PlayerId, Card>
//        for (i in cards.indices) {
//            val card = cards[i]
//            if (card != null) {
//                all.put(moveBy)
//            }
//        }
//    }
//
//    private fun lastCardIndex() : #(Int, Card) {
//        for (i in (cards.size - 1) .. 0) {
//            val card = cards[i]
//            if (card != null) {
//                return #(i, card)
//            }
//        }
//
//        throw IllegalStateException("At least first card can't be null")
//    }
//
//
//    fun isFinished() : Boolean = thirdCard != null
//
//    fun trickBy(trump : Suit) : PlayerId {
//        val all = cards.filterNotNull()
//        if (all.size != cards.size) {
//            throw IllegalStateException("Round isn't finished")
//        }
//
//        if (all.any({it.suit == trump})) {
//            return moveBy
//            // return moveBy.shift(bustIndexWithSuit(trump, all))
//        }
//        return moveBy
//        // return moveBy.shift(bustIndexWithSuit(firstCard.suit, all))
//    }
//
//    private fun bestIndexWithSuit(suit : Suit, cards : List<Card>) : Int {
//        var bestValue = -1
//        var bestIndex = -1
//        var curIndex = 0
//        while (curIndex < cards.size) {
//            if (cards[curIndex].suit == suit) {
//                if (cards[curIndex].value.number > bestValue) {
//                    bestValue = cards[curIndex].value.number
//                    bestIndex = curIndex
//                }
//            }
//
//            curIndex++
//        }
//
//        println(bestIndex)
//
//        return bestIndex
//    }
}