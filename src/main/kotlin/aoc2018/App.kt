package aoc2018

fun main(args: Array<String>) {
    val programs = hashMapOf(
        "01A" to (Exercise01::sumCalibration to "/01"),
        "01B" to (Exercise01::sumCalibrationCicles to "/01"),
        "02A" to (Exercise02::checksumBoxes to "/02"),
        "02B" to (Exercise02::findBox to "/02"),
        "03A" to (Exercise03::countIntersections to "/03"),
        "03B" to (Exercise03::checkClean to "/03"),
        "04A" to (Exercise04::sleepingGuard to "/04"),
        "04B" to (Exercise04::guardMinuteFrequency to "/04"),
        "05A" to (Exercise05::reactPolymerLength to "/05"),
        "05B" to (Exercise05::reactBestPolymer to "/05"),
        "06A" to (Exercise06::sizeOfLargestArea to "/06"),
        "06B" to (Exercise06::sizeOfSafestArea to "/06")
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
