package aoc2018

import java.math.BigInteger

data class Rule(val pattern: String, val value: String)

class PotState(val state: String, val from: Int, val to: Int) {
    constructor(state: String) : this(state, 0, state.length - 1) {}

    fun strIdx(index: Int) = index - this.from

    fun window(index: Int, offsetStart: Int = -2, offsetEnd: Int = 2): String =
        (index + offsetStart .. index + offsetEnd).fold(StringBuilder()) { str, index ->
            str.append(this.state.getOrNull(strIdx(index)) ?: ".")
        }.toString()

    fun set(index: Int, value: String): PotState {
        if ((this.state.getOrNull(strIdx(index)) ?: ".") == value) {
            // noop
            return this
        }

        if (index < this.from) {
            val str = StringBuilder(value)
            val prefix = (index + 1 .. this.from - 1).fold(StringBuilder()) { acc, _ -> acc.append(".") }.toString()
            str.append(prefix)
            str.append(this.state)
            return PotState(str.toString(), index, this.to)
        } else if (index > this.to) {
            val str = StringBuilder(this.state)
            val sufix = (this.to + 1 .. index - 1).fold(StringBuilder()) { acc, _ -> acc.append(".") }.toString()
            str.append(sufix)
            str.append(value)
            return PotState(str.toString(), this.from, index)
        } else {
            val str = StringBuilder(this.state.subSequence(0, strIdx(index)))
            str.append(value)
            str.append(this.state.subSequence(strIdx(index) + 1, this.state.length))
            return PotState(str.toString(), this.from, this.to)
        }
    }

    fun value(): Int {
        return (this.from .. this.to).fold(0) { acc, v -> acc + ( if (this.state[strIdx(v)] == '#') v else 0) }
    }
}

object Exercise12 {
    fun executeRules(oldState: PotState, rules: List<Rule>): PotState =
        (oldState.from - 2 .. oldState.to + 2).fold(oldState) { newState, index ->
            val current = oldState.window(index, -2, 2)
            val rule = rules.find { it.pattern == current }
            newState.set(index, rule?.value ?: ".")
        }

    fun iterateGenerations(state: PotState, rules: List<Rule>, generations: Int): PotState {
        var current = 0
        var result = state

        while (current < generations) {
            result = this.executeRules(result, rules)
            current++
        }

        return result
    }

    fun parseFirstLine(line: String): String {
        val regex = Regex("initial state: (.*)")
        val (result) = regex.matchEntire(line)!!.destructured
        return result
    }

    fun parseRule(line: String): Rule {
        val regex = Regex("([^\\s]+) => (.)")
        val (pattern, value) = regex.matchEntire(line)!!.destructured
        return Rule(pattern, value)
    }

    fun sumPotsWithPlants(lines: List<String>): Int {
        val rules = lines.drop(2).map(::parseRule)
        val state = PotState(parseFirstLine(lines[0]))
        return iterateGenerations(state, rules, 20).value()
    }

    fun sumPotsWithPlants2(lines: List<String>): BigInteger {
        val rules = lines.drop(2).map(::parseRule)
        var state = PotState(parseFirstLine(lines[0]))

        var value = state.value()
        var increase = 0

        val iter = 150

        for (i in (1 .. iter)) {
            state = executeRules(state, rules)
            increase = value - state.value()
            value = state.value()
        }

        val target: BigInteger = 50000000000.toBigInteger()
        return b(value) + ((target - b(iter)) * b(80))
    }
}
