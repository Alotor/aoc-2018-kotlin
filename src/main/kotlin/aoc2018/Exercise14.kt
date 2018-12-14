package aoc2018

object Exercise14 {
    val first = 3
    val second = 7

    fun split(num: Int): Pair<Int, Int> {
        val decs = num / 10
        val units = num - decs * 10
        return decs to units
    }

    fun generate(cond: (current: List<Int>) -> Boolean): List<Int> {
        val result = mutableListOf<Int>()
        result.add(first)
        result.add(second)

        var firstIdx = 0
        var secondIdx = 1

        while (!cond(result)) {
            val next = result[firstIdx] + result[secondIdx]
            val (decs, units) = split(next)

            if (decs != 0) {
                result.add(decs)
            }

            if (!cond(result)) {
                result.add(units)
            }

            firstIdx = (firstIdx + result[firstIdx] + 1) % result.size
            secondIdx = (secondIdx + result[secondIdx] + 1) % result.size
        }

        return result.toList()
    }

    fun last10(num: Int): List<Int> =
        generate { it.size == num + 10 }.drop(num)

    fun tailGen(target: String): Int {
        val result = generate {
            it.size >= target.length && it.subList(it.size - target.length, it.size).joinToString("") == target
        }
        return result.size - target.length
    }

    fun calculateLas10(input: List<String>): String =
        last10(input[0].toInt()).joinToString("")

    fun calculateTailGen(input: List<String>): Int =
        tailGen(input[0])
}
