package cards

class OrdinalGameState private (private val moveBy : PlayerId,
                                private val playerStates : Array<PlayerState>,
                                private val trump : Suit,
                                private val roundState : RoundState,
                                private val moveNumber : Int = 0) {
    class object {
//        fun createState(main: PlayerState, right : PlayerState, left: PlayerState,
//                        moveBy : PlayerId, trump : Suit) : RoundState {
//
//        }
//
//        fun updateState(state : OrdinalGameState, round : RoundState) {
//            val lastMove = round.lastPlayedCard()
//            val playerId = lastMove._1
//            val playedCard = lastMove._2
//
//            if (playerId != state.moveBy) {
//                throw IllegalStateException("Invalid round state")
//            }
//        }

        private fun getPlayersStates(state : OrdinalGameState, move : #(PlayerId, Card)) {

        }

        private fun getPlayerIndex(playerId : PlayerId) : Int {
            when (playerId) {
                PlayerId.Main -> 0
                PlayerId.Left -> 1
                PlayerId.Right -> 2
            }

            throw IllegalStateException("Should never happen")
        }
    }

    fun getPlayerState(playerId : PlayerId) : PlayerState {
        return playerStates[getPlayerIndex(playerId)]
    }

    val activePlayerState = getPlayerState(moveBy)

    val isNewRound : Boolean = moveNumber % 3 == 0

    public fun hashCode() : Int {
        return 0
    }
}