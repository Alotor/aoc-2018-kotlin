package aoc2018

import java.time.LocalDate
import java.time.format.DateTimeFormatter


// Date -> (Id, [TimeAsleep])
typealias Store = MutableMap<String, Pair<Int, List<IntRange>>>

object Exercise04 {
    val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")

    fun nextDate(sdate: String): String = LocalDate
            .parse(sdate, formatter)
            .plusDays(1)
            .format(formatter)

    fun parseSchedule(lines: List<String>): Store {
        val store: Store = mutableMapOf()

        val entryReg = Regex("\\[(\\d{4}-\\d{2}-\\d{2}) (\\d{2}):(\\d{2})\\] (.*)")
        val beginsReg = Regex("Guard #(\\d+) begins shift")

        var currentGuard: Int
        var startSleep: Int? = null

        for (line in lines) {
            // println(line)
            val match = entryReg.matchEntire(line)!!
            val (sdate, hour, minute, log) = match.destructured
            val date = if (hour == "23") nextDate(sdate) else sdate

            when (log) {
                "falls asleep" -> {
                    startSleep = minute.toInt()
                }

                "wakes up" -> {
                    val newp: IntRange = IntRange(startSleep!!, minute.toInt() - 1)
                    val newl: List<IntRange> = store[date]!!.second.plusElement(newp)
                    store[date] = Pair(store[date]!!.first, newl)
                }

                else -> {
                    val logMatch = beginsReg.matchEntire(log)!!
                    currentGuard = logMatch.groups[1]!!.value.toInt()
                    startSleep = null
                    store[date] = currentGuard to listOf<IntRange>()
                }

            }
        }

        return store
    }

    fun mostSleepGuard(store: Store): Int {
        val grouped = store.values.groupBy(
            { it.first },
            { it.second.map { it.count() }.sum() }
        )
        return grouped.mapValues { it.value.sum() }
                      .maxBy { it.value }!!.key
    }

    fun probableSleepTime(store: Store, guard: Int): Int {
        val sleepRanges = store
                .filterValues { it.first == guard }
                .values
                .flatMap { it.second }

        return (0 .. 59).maxBy { minute ->
            sleepRanges.count { it.contains(minute) }
        }!!
    }

    fun sleepingGuard(lines: List<String>): Int {
        val store = parseSchedule(lines.sortedWith(compareBy { it }))
        val mostSleepGuard = mostSleepGuard(store)
        val probableMinute = probableSleepTime(store, mostSleepGuard)
        return mostSleepGuard * probableMinute
    }

    fun mostSleepedGuard(store: Store, minute: Int): Pair<Int?, Int?> {
        val maxGuardEntry = store
                .filterValues { it.second.any { it.contains(minute) }}
                .values
                .map { it.first }
                .groupBy{ it }
                .maxBy { it.value.size }

        return maxGuardEntry?.key to maxGuardEntry?.value?.size
    }

    fun guardMinuteFrequency(lines: List<String>): Int {
        val store = parseSchedule(lines.sortedWith(compareBy { it }))

        var maxTimes: Int? = null
        var maxGuard: Int? = null
        var maxMinute: Int? = null

        for (minute in 0 .. 59) {
            val (guard, times) = mostSleepedGuard(store, minute)

            if (guard == null || times == null) {
                continue
            }

            if (maxTimes == null || times > maxTimes) {
                maxTimes = times
                maxGuard = guard
                maxMinute = minute
            }
        }

        return maxGuard!! * maxMinute!!
    }

}
