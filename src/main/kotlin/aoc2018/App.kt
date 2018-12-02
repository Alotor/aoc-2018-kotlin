package aoc2018

fun main(args: Array<String>) {
    val programs = hashMapOf(
        "01A" to (Exercise01::sumCalibration to "/01A"),
        "01B" to (Exercise01::sumCalibrationCicles to "/01B"),
        "02A" to (Exercise02::checksumBoxes to "/02A"),
        "02B" to (Exercise02::findBox to "/02B")
    )

    when (val pair = programs[args[0]]) {
        is Pair -> {
            val resource = Utils.resolveResource(pair.second)
            val lines = Utils.readLines(resource)
            print(pair.first(lines))
        }
        else -> print("ERROR")
    }
}
