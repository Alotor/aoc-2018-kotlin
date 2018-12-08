package aoc2018

import kotlin.test.*

class Exercise08Test {

    @Test fun testSumMetadataEntries() {
        val input = arrayOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)
        val res = Exercise08.recursiveTreeValue(input, 0, Exercise08::metadataValue)
        assertEquals(138, res.first)
    }

    @Test fun testSumChildrenValues() {
        val input = arrayOf(2, 3, 0, 3, 10, 11, 12, 1, 1, 0, 1, 99, 2, 1, 1, 2)
        val res = Exercise08.recursiveTreeValue(input, 0, Exercise08::childrenValue)
        assertEquals(66, res.first)
    }

}
