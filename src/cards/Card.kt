package cards

class Card(val suit : Suit, val value : CardValue) {
    public fun toString() : String {
        return value.toString() + suit.toString()
    }
}