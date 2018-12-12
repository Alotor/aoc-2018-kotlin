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
        "06B" to (Exercise06::sizeOfSafestArea to "/06"),
        "07A" to (Exercise07::assembleOrder to "/07"),
        "07B" to (Exercise07::assembleOrderWithWorkers to "/07"),
        "08A" to (Exercise08::calculateMetadataSum to "/08"),
        "08B" to (Exercise08::calculateMetadataChildrenValue to "/08"),
        "09A" to (Exercise09::gameHighscore to "/09"),
        "09B" to (Exercise09::gameHighscorex100 to "/09"),
        "10A" to (Exercise10::calculateLightsMessage to "/10"),
        "10B" to (Exercise10::calculateLightsTime to "/10"),
        "11A" to (Exercise11::calculateMaxPowerCell to "/11"),
        "11B" to (Exercise11::calculateMaxPowerCellAnySize to "/11"),
        "12A" to (Exercise12::sumPotsWithPlants to "/12"),
        "12B" to (Exercise12::sumPotsWithPlants2 to "/12")
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
