package aoc2018

import kotlin.test.*

class Exercise12Test {

    @Test fun testStateWindow() {
        val state = PotState("#####")
        assertEquals("..###", state.window(0))
        assertEquals("###..", state.window(4))
        assertEquals("#####", state.window(2))
        assertEquals(".....", state.window(100))
        assertEquals(".....", state.window(-100))
    }

    @Test fun testSetStateRangeBetween() {
        val state = PotState("#####").set(2, ".")
        assertEquals("..##.##..", state.window(2, -4, 4))
    }

    @Test fun testSetStateBefore() {
        val state = PotState("#####").set(-5, "#")
        assertEquals(-5, state.from)
        assertEquals(4, state.to)
        assertEquals(".....#....#####......", state.window(0, -10, 10))
    }

    @Test fun testSetStateAfter() {
        val state = PotState("#####").set(10, "#")
        assertEquals(0, state.from)
        assertEquals(10, state.to)
        assertEquals("#####.....#..........", state.window(0, 0, 20))
    }

    @Test fun testExecuteRules() {
        val state = PotState("...#.#..#...#.#...#..#..##..##.........", -3, 35)

        val rules = listOf(
            Rule("...##", "#"),
            Rule("..#..", "#"),
            Rule(".#...", "#"),
            Rule(".#.#.", "#"),
            Rule(".#.##", "#"),
            Rule(".##..", "#"),
            Rule(".####", "#"),
            Rule("#.#.#", "#"),
            Rule("#.###", "#"),
            Rule("##.#.", "#"),
            Rule("##.##", "#"),
            Rule("###..", "#"),
            Rule("###.#", "#"),
            Rule("####.", "#")
        )

        val result = Exercise12.executeRules(state, rules)
        assertEquals("....#...##...#.#..#..#...#...#.........", result.window(0, -3, 35))
    }

    @Test fun testIterateGenerations() {
        val state = PotState("#..#.#..##......###...###")

        val rules = listOf(
            Rule("...##", "#"),
            Rule("..#..", "#"),
            Rule(".#...", "#"),
            Rule(".#.#.", "#"),
            Rule(".#.##", "#"),
            Rule(".##..", "#"),
            Rule(".####", "#"),
            Rule("#.#.#", "#"),
            Rule("#.###", "#"),
            Rule("##.#.", "#"),
            Rule("##.##", "#"),
            Rule("###..", "#"),
            Rule("###.#", "#"),
            Rule("####.", "#")
        )

        val result = Exercise12.iterateGenerations(state, rules, 20)
        assertEquals(".#....##....#####...#######....#.#..##.", result.window(0, -3, 35))
    }

    @Test fun testStateValue() {
        val state = PotState(".#....##....#####...#######....#.#..##.", -3, 35)
        assertEquals(325, state.value())
    }
}
