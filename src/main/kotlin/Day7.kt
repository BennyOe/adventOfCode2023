import java.io.File

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day7/input")
        var decks = file.readLines().map { line -> line.split(" ") }.map { (k, v) -> HandPart2(k, v.toInt()) }
            .sortedBy { it.sortOrder }.toList()

        for (i in decks.indices) {
            for (j in 1 until decks.size) {
                if (decks[i].sortOrder == decks[j].sortOrder) {
                    if (decks[i].isBetterThan(decks[j].cards)) decks[i].cardOrder += 1
                }
            }
        }
        decks = decks.sortedWith(compareBy(HandPart2::sortOrder, HandPart2::cardOrder))

        val sum = decks.withIndex().sumOf { (index, hand) -> (index + 1) * hand.money }
        println(sum)
    }

}

data class Hand(val cards: String, val money: Int, var sortOrder: Int, var cardOrder: Int) {
    constructor(cards: String, money: Int) : this(cards, money, calculateSortOrder(cards), 0)

    fun isBetterThan(other: String): Boolean {
        var isBetter = false
        for (i in cards.indices) {
            if (getCardValue(cards[i]) > getCardValue(other[i])) isBetter = true
            if (getCardValue(cards[i]) == getCardValue(other[i])) continue
            break
        }
        return isBetter
    }

    private fun getCardValue(card: Char): Int {
        return when (card) {
            'A' -> 20
            'K' -> 19
            'Q' -> 18
            'J' -> 17
            'T' -> 16
            else -> card.digitToInt()
        }
    }

    companion object {
        fun calculateSortOrder(cards: String): Int {
            val groupedCards = cards.groupBy { it }
            val counts = groupedCards.values.map { it.size }
            return when {
                counts.contains(5) -> 600
                counts.contains(4) -> 500
                counts.contains(3) && counts.contains(2) -> 400
                counts.contains(3) -> 300
                counts.count { it == 2 } == 2 -> 200
                counts.count { it == 2 } == 1 && counts.count { it == 1 } == 3 -> 100
                else -> 0
            }
        }
    }
}
