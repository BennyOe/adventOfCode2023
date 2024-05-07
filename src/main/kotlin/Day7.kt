import java.io.File

data class Hand(val cards: String, val money: Int, var sortOrder: Int) {
    constructor(cards: String, money: Int) : this(cards, money, calculateSortOrder(cards))

    fun isBetterThan(other: String): Boolean {
        var isBetter = false
        for (i in cards.indices) {
            if (getCardValue(cards[i]) > getCardValue(other[i])) isBetter = true
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
            return getDeckValue(cards)
        }

        private fun getDeckValue(card: String): Int {
            return when {
                isFiveOfAKind(card) -> 600
                isFourOfAKind(card) -> 500
                isFullHouse(card) -> 400
                isThreeOfAKind(card) -> 300
                isTwoPairs(card) -> 200
                isOnePair(card) -> 100
                else -> 0
            }
        }

        private fun isFiveOfAKind(card: String) =
            countCharacters(card).containsValue(5)

        private fun isFourOfAKind(card: String) =
            countCharacters(card).containsValue(4)

        private fun isFullHouse(card: String): Boolean {
            val countMap = countCharacters(card)
            return countMap.containsValue(3) && countMap.containsValue(2)
        }

        private fun isThreeOfAKind(card: String): Boolean {
            val countMap = countCharacters(card)
            return countMap.containsValue(3) && !countMap.containsValue(2)
        }

        private fun isTwoPairs(card: String): Boolean {
            val countMap = countCharacters(card)
            val countTwo = countMap.count { (_, v) -> v == 2 }
            return countTwo == 2
        }

        private fun isOnePair(card: String): Boolean {
            val countMap = countCharacters(card)
            val countTwo = countMap.count { (_, v) -> v == 2 }
            val countOne = countMap.count { (_, v) -> v == 1 }
            return countTwo == 1 && countOne == 3
        }

        private fun countCharacters(string: String): Map<Char, Int> {
            val result = mutableMapOf<Char, Int>()
            string.forEach { char ->
                if (!result.containsKey(char)) result[char] = 0
                if (result.containsKey(char)) result[char] = result[char]!! + 1
            }
            return result
        }
    }
}

object Day7 {

    @JvmStatic
    fun main(args: Array<String>) {
        val file = File("src/main/resources/day7/input")
        var decks = file.readLines().map { line -> line.split(" ") }.map { (k, v) -> Hand(k, v.toInt()) }
            .sortedBy { it.sortOrder }.toList()

        var sum = 0
        for (i in 0 until  decks.size - 1) {
            if (decks[i].sortOrder == decks[i + 1].sortOrder) {
                if (decks[i].isBetterThan(decks[i + 1].cards)) decks[i].sortOrder += 1
                else decks[i + 1].sortOrder += 1
            }
        }
        decks = decks.sortedBy { it.sortOrder }

        for ((index, hand) in decks.withIndex()) {
            sum += (index + 1) * hand.money
        }
        println(sum)
    }

}