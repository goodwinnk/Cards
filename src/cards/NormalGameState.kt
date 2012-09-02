package cards

import java.util.List
import java.util.HashMap
import java.util.Collection
import java.util.ArrayList

fun initNormalGameState(
        hands: List<Hand>,
        roundState: RoundState,
        activePlayerIndex: Int
): NormalGameState {
    if (hands.size() != 3) {
        throw IllegalArgumentException("There should be 3 hands")
    }

    if (!(activePlayerIndex >= 0 && activePlayerIndex < 3)) {
        throw IllegalArgumentException("Invalid index of active player")
    }

    return NormalGameState(hands, roundState, activePlayerIndex)
}

// GameState -> Number of Tricks
private val statesCache: HashMap<NormalGameState, Int> = HashMap<NormalGameState, Int>()

public fun nextPlayerIndex(activePlayerIndex: Int): Int {
    return nextPlayerIndex(activePlayerIndex, 0)
}

public fun nextPlayerIndex(activePlayerIndex: Int, winnerShift: Int): Int {
    return (activePlayerIndex + 1 + winnerShift) % 3
}

public fun getPossibleMoves(hand: Hand, roundState: RoundState, trump: Suit?): Collection<Card> {
    if (!roundState.isEmpty) {
        val moveSuit = roundState.firstMoveSuit
        if (moveSuit == null) {
            throw IllegalStateException("Suit can't be null for non-empty round")
        }

        val suitCards = hand.getSuitCards(moveSuit)
        if (!suitCards.isEmpty()) {
            return suitCards
        }

        if (trump != null) {
            val trumpCards = hand.getSuitCards(trump)
            if (!trumpCards.isEmpty()) {
                return trumpCards
            }
        }
    }

    return hand.cards
}

public open class NormalGameState(
        private val hands: List<Hand>,
        private val roundState: RoundState,
        private val activePlayerIndex: Int
) {
    public fun numOfTricksWithTrump(trumpSuit: Suit?, declarerIndex: Int): Int {
        // Temp progress print
        if (hands.get(0).cards.size() == 7) {
            println(this)
        }

        val activePlayerHand = hands.get(activePlayerIndex)
        var trickResult = if (activePlayerIndex == declarerIndex) 0 else Integer.MAX_VALUE

        for (moveCard in getPossibleMoves(activePlayerHand, roundState, trumpSuit)) {
            val newHand = activePlayerHand.removeCard(moveCard)
            val newRoundState = roundState.addCard(moveCard)

            val newHands = ArrayList<Hand>(hands)
            newHands.set(activePlayerIndex, newHand)

            val newGameState: NormalGameState
            var roundTrickNumber = 0

            val newActivePlayerIndex: Int

            if (newRoundState.isFinished) {
                newActivePlayerIndex = nextPlayerIndex(activePlayerIndex, newRoundState.indexOfWinner(trumpSuit))

                if (newActivePlayerIndex == declarerIndex) {
                    roundTrickNumber = 1
                }

                if (newHand.isEmpty()) {
                    // New active player can't do a move - The game is finished
                    // println("Final for ${this} = ${roundTrickNumber}")
                    return roundTrickNumber
                }

                newGameState = NormalGameState(newHands, initRoundState(), newActivePlayerIndex)
            } else {
                newActivePlayerIndex = nextPlayerIndex(activePlayerIndex)
                newGameState = NormalGameState(newHands, newRoundState, newActivePlayerIndex)
            }

            val stateTricks: Int

            if (newGameState.roundState.isEmpty) {
                if (statesCache.containsKey(newGameState)) {
                    stateTricks = statesCache.get(newGameState)!!
                    // println("CACHE ====>>>> for ${newGameState} = ${stateTricks}")
                } else {
                    stateTricks = newGameState.numOfTricksWithTrump(trumpSuit, declarerIndex)
                    // println("CACHE <<<<==== for ${newGameState} = ${stateTricks}")
                    statesCache.put(newGameState, stateTricks)
                }
            } else {
                stateTricks = newGameState.numOfTricksWithTrump(trumpSuit, declarerIndex)
            }

            val tricks: Int = stateTricks + roundTrickNumber
            trickResult = if (activePlayerIndex == declarerIndex) Math.max(trickResult, tricks) else Math.min(trickResult, tricks)
        }

        // println("Final for ${this} = ${trickResult}")

        return trickResult
    }

    private var hashCodeCache: Int = 0
    private var hashCodeReady: Boolean = false

    public fun hashCode(): Int {
        if (!hashCodeReady) {
            hashCodeCache = 17
            for (hand in hands) {
                hashCodeCache = 31 * hashCodeCache + hand.hashCode()
            }
            hashCodeCache = 31 * hashCodeCache + activePlayerIndex
            hashCodeCache = 31 * hashCodeCache + roundState.hashCode()

            hashCodeReady = true
        }

        return hashCodeCache
    }

    public fun equals(obj: Any?): Boolean {
        if (!(obj is NormalGameState)) {
            return false
        }

        val otherState = (obj as NormalGameState)
        if (activePlayerIndex != otherState.activePlayerIndex) {
            return false
        }

        for (i in hands.size() - 1 downTo 0) {
            if (hands.get(i) != otherState.hands.get(i)) {
                return false
            }
        }

        return roundState == otherState.roundState
    }

    public fun toString(): String {
        val builder = StringBuilder("State: ")
        for (i in 0..hands.size - 1) {
            val hand = hands.get(i)
            if (i == activePlayerIndex) {
                builder.append("!")
            }

            builder.append(hand)?.append(" ")
        }

        return builder.toString()!!
    }
}

