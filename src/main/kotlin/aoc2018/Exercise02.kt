package aoc2018

object Exercise02 {
    fun letterFreq(word: String): Pair<Int, Int> =
        word.groupBy({ it })
            .entries
            .fold(Pair(0, 0), { acc, v ->
                val count = v.value.count()
                if (count == 2) {
                    Pair(1, acc.second)
                } else if (count == 3) {
                    Pair(acc.first, 1)
                } else {
                    acc
                }
            })

    fun checksumBoxes(lines: List<String>): Int {
        val res = lines
                .map(::letterFreq)
                .reduce({ acc, v -> Pair(
                        acc.first + v.first,
                        acc.second + v.second)})

        return res.first * res.second
    }

    fun singleCharacterDiff(str1: String?, str2: String?): Int? {
        if (str1 == null || str2 == null || str1.length != str2.length) {
            return null
        }

        var diffPosition: Int? = null
        for (i in str1.indices) {
            if (str1[i] != str2[i]) {
                if (diffPosition != null) {
                    return null
                } else {
                    diffPosition = i
                }
            }
        }

        return diffPosition
    }

    fun findBox(lines: List<String>): String {
        var rest: List<String>

        for (w1 in lines) {
            rest = lines.drop(1)

            val w2 = rest.find { w2 ->
                singleCharacterDiff(w1, w2) != null
            }

            if (w2 != null) {
                val diff = singleCharacterDiff(w1, w2)!!
                return w1.substring(0, diff) + w1.substring(diff + 1)
            }
        }

        return "?"
    }
}
