package aoc2018

val MAX_ITERATIONS = 10000

object Exercise01 {
    fun sumCalibration(input: List<String>): Int =
        input.map(String::toInt).sum()

    fun sumCalibrationCicles(input: List<String>): Int {
        var currentAccumulated = 0
        var cicles = HashSet<Int>(0)
        var iterations = 0

        while ((iterations++) <= MAX_ITERATIONS) {
            for (item in input) {
                currentAccumulated += item.toInt()

                if (cicles.contains(currentAccumulated)) {
                    return currentAccumulated
                }
                cicles.add(currentAccumulated)
            }
        }

        // Over the maximum iterations allowed 
        return -1;
    }
}
