package aoc2018

import kotlin.test.*

class Exercise04Test {
    val textSchedule = listOf(
        "[1518-11-01 00:00] Guard #10 begins shift",
        "[1518-11-01 00:05] falls asleep",
        "[1518-11-01 00:25] wakes up",
        "[1518-11-01 00:30] falls asleep",
        "[1518-11-01 00:55] wakes up",
        "[1518-11-01 23:58] Guard #99 begins shift",
        "[1518-11-02 00:40] falls asleep",
        "[1518-11-02 00:50] wakes up",
        "[1518-11-03 00:05] Guard #10 begins shift",
        "[1518-11-03 00:24] falls asleep",
        "[1518-11-03 00:29] wakes up",
        "[1518-11-04 00:02] Guard #99 begins shift",
        "[1518-11-04 00:36] falls asleep",
        "[1518-11-04 00:46] wakes up",
        "[1518-11-05 00:03] Guard #99 begins shift",
        "[1518-11-05 00:45] falls asleep",
        "[1518-11-05 00:55] wakes up"
    )

    @Test fun testParseSchedule() {
        val result = Exercise04.parseSchedule(this.textSchedule)
        assertEquals(result["1518-11-01"]!!.first, 10)
        assertEquals(result["1518-11-01"]!!.second, listOf(IntRange(5, 24), IntRange(30, 54)))
        assertEquals(result["1518-11-02"]!!.first, 99)
        assertEquals(result["1518-11-02"]!!.second, listOf(IntRange(40, 49)))
        assertEquals(result["1518-11-03"]!!.first, 10)
        assertEquals(result["1518-11-03"]!!.second, listOf(IntRange(24, 28)))
        assertEquals(result["1518-11-04"]!!.first, 99)
        assertEquals(result["1518-11-04"]!!.second, listOf(IntRange(36, 45)))
        assertEquals(result["1518-11-05"]!!.first, 99)
        assertEquals(result["1518-11-05"]!!.second, listOf(IntRange(45, 54)))
    }

    @Test fun testMostSleepingGuard() {
        val store = Exercise04.parseSchedule(textSchedule)
        val result = Exercise04.mostSleepGuard(store)
        assertEquals(result, 10)
    }

    @Test fun testMostProbableMinuteSleeping() {
        val store = Exercise04.parseSchedule(textSchedule)
        val result = Exercise04.probableSleepTime(store, 10)
        assertEquals(result, 24)
    }

    @Test fun testGuardMinuteFrequency() {
        val result = Exercise04.guardMinuteFrequency(textSchedule)
        assertEquals(result, 4455)
    }

}
