package aoc2018

object Exercise05 {
    fun collide(a: Char, b: Char) =
        a.isLowerCase() && b.isUpperCase() && a.toUpperCase() == b ||
        a.isUpperCase() && b.isLowerCase() && a.toLowerCase() == b

    fun reactPolymer(polymer: String): String {
        val output = arrayOfNulls<Char?>(polymer.length)
        var curIdx = 0

        polymer.forEach { current -> when {
            curIdx == 0 ->
                output[curIdx ++] = current

            collide(current, output[curIdx - 1]!!) ->
                curIdx --

            else ->
                output[curIdx++] = current
            }
        }

        return output.joinToString("", "", "", curIdx, "")
    }

    fun reactPolymerLength(lines: List<String>): Int = reactPolymer(lines[0]).length

    fun reactBestPolymer(lines: List<String>): Int =
        ('a' .. 'z')
            .map { ch -> Pair(ch, reactPolymer(lines[0].replace("$ch", "", true)).length)}
            .minBy { it.second }!!
            .second
}
