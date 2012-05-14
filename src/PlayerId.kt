package cards

enum class PlayerId(val id : Int) {
    Main : PlayerId(0)
    Left : PlayerId(1)
    Right : PlayerId(2)

    //    fun shift(number : Int) : PlayerId {
    //        // return PlayerId((this.id + number) % 3) // doesn't work
    //        return this
    //    }

    fun toString() = when (this) {
        Main -> "Main"
        Left -> "Left"
        Right -> "Right"
        else -> "Error"
    }
}

fun shift(playerId : PlayerId, var shiftTo : Int) : PlayerId {
    shiftTo = shiftTo % 3
    if (shiftTo == 0) {
        return playerId
    }

    when (playerId) {
        PlayerId.Main -> return if (shiftTo == 1) PlayerId.Left else PlayerId.Right
        PlayerId.Left -> return if (shiftTo == 1) PlayerId.Right else PlayerId.Main
        PlayerId.Right -> return if (shiftTo == 1) PlayerId.Main else PlayerId.Left
    }

    throw IllegalStateException()
}