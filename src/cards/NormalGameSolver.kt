package cards

import java.util.HashSet
import java.util.HashMap
import java.util.ArrayList
import java.util.Collection
import java.util.Set

class NormalGameSolver(initState : OrdinalGameState, trump : Suit) {
    private var currentState = initState
    private var trump = trump

    // Move number -> State
    private val processedStates = Array(30, { HashSet<OrdinalGameState>() })

    // Processed state -> Stored state analytics
    private val storedDecisions = HashMap<OrdinalGameState, StateDecision>()

    fun solve() : Int {
        val test = array(12, 13, 14)
        test.forEach {
            println(it)
        }
        return 0
    }

//    fun doMove() : OrdinalGameState {
//        val activePlayerState = currentState.activePlayerState
//        val moves = if (currentState.isNewRound) {
//            possibleMovesForFirstInRound(activePlayerState)
//        } else {
//            // possibleMovesForPlayer(activePlayerState, currentState.)
//        }
//
//
//    }

    class object {
        public fun possibleMovesForFirstInRound(activePlayerState : PlayerState) : Set<Card> {
            val allMoves = HashSet<Card>()
            allMoves.addAll(getPossibleCards(activePlayerState.suitSet(Suit.Spades), Suit.Spades))
            allMoves.addAll(getPossibleCards(activePlayerState.suitSet(Suit.Clubs), Suit.Clubs))
            allMoves.addAll(getPossibleCards(activePlayerState.suitSet(Suit.Diamonds), Suit.Diamonds))
            allMoves.addAll(getPossibleCards(activePlayerState.suitSet(Suit.Hearts), Suit.Hearts))

            return allMoves
        }

        public fun possibleMovesForPlayer(activePlayerState : PlayerState, firstCard : Card, trump : Suit) : Collection<Card> {
            val suitSet = activePlayerState.suitSet(firstCard.suit)

            if (suitSet.isEmpty() && firstCard.suit != trump) {
                // If player doesn't have correspondent suit - try to make move with trump
                return getPossibleCards(activePlayerState.suitSet(trump), trump)
            }

            return getPossibleCards(suitSet, firstCard.suit)
        }

        private fun getPossibleCards(suitSet : Array<Boolean>, suit : Suit) : Collection<Card> {
            val cards = ArrayList<Card>()

            var gap = true
            for (i in suitSet.indices) {
                if (suitSet[i]) {
                    if (gap) {
                        // if we have 8, 9, and Jack - possible moves will be Jack and 9. There are difference for
                        // further game if we move with 8 or 9
                        cards.add(Card(suit, cardValue(i)))
                    }
                    gap = false
                } else {
                    gap = true
                }
            }

            return cards
        }
    }
}