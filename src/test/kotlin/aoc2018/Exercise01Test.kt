package aoc2018

import kotlin.test.*

class Exercise01Test {
    @Test fun testSumCalibration1() {
        // given
        val input = listOf("-1", "+1", "+1")

        // when
        val result = Exercise01.sumCalibration(input)

        // then
        assertEquals(result, 1)
    }

    @Test fun testSumCalibration2() {
        // given
        val input = listOf("-1", "-2", "-3")

        // when
        val result = Exercise01.sumCalibration(input)

        // then
        assertEquals(result, -6)
    }

    @Test fun testSumCalibrationCicles1() {
        // given
        val input = listOf("+3", "+3", "+4", "-2", "-4")

        // when
        val result = Exercise01.sumCalibrationCicles(input)

        // then
        assertEquals(result, 10)
    }

    @Test fun testSumCalibrationCicles2() {
        // given
        val input = listOf("-6", "+3", "+8", "+5", "-6")

        // when
        val result = Exercise01.sumCalibrationCicles(input)

        // then
        assertEquals(result, 5)
    }



}
