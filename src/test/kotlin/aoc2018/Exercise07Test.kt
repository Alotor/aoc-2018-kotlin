package aoc2018

import kotlin.test.*

class Exercise07Test {
    val input = listOf(
            "Step C must be finished before step A can begin.",
            "Step C must be finished before step F can begin.",
            "Step A must be finished before step B can begin.",
            "Step A must be finished before step D can begin.",
            "Step B must be finished before step E can begin.",
            "Step D must be finished before step E can begin.",
            "Step F must be finished before step E can begin."
    )

    @Test fun testBuildTreeStructure() {
        val res = Exercise07.buildChildrenTree(this.input.map(Exercise07::parse))
        assertEquals(res["C"], listOf("A", "F"))
        assertEquals(res["A"], listOf("B", "D"))
        assertEquals(res["B"], listOf("E"))
        assertEquals(res["D"], listOf("E"))
        assertEquals(res["F"], listOf("E"))
    }

    @Test fun testAssembleOrder() {
        val res = Exercise07.assembleOrder(this.input)
        assertEquals("CABDFE", res)
    }

    @Test fun testAssembleOrderWithWorkers() {
        Exercise07.workers = 2
        Exercise07.time = 0
        val res = Exercise07.assembleOrderWithWorkers(this.input)
        assertEquals("CABFDE" to 15, res)
    }
}
