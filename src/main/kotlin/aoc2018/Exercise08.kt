package aoc2018

typealias VFN = (Array<Int>, Array<Int>) -> Int

object Exercise08 {
    fun metadataValue(childrenValues: Array<Int>, metadataValues: Array<Int>): Int =
        childrenValues.sum() + metadataValues.sum()

    fun childrenValue(childrenValues: Array<Int>, metadataValues: Array<Int>): Int =
        if (childrenValues.size == 0) {
            metadataValue(childrenValues, metadataValues)
        } else {
            metadataValues.map { childrenValues.getOrNull(it - 1) ?: 0 }.sum()
        }

    fun recursiveTreeValue(input: Array<Int>, currentIndex: Int, valueFn: VFN): Pair<Int, Int> {
        var index = currentIndex
        val childNum = input[index++]
        val metadataNum = input[index++]
        val childValues = Array<Int>(childNum, { 0 })
        val metadataValues = Array<Int>(metadataNum, { 0 })

        (0 .. (childNum - 1)).forEach {
            val (childValue, newIndex) = recursiveTreeValue(input, index, valueFn)
            index = newIndex
            childValues[it] = childValue
        }

        (0 .. (metadataNum - 1)).forEach {
            metadataValues[it] = input[index++]
        }

        return valueFn(childValues, metadataValues) to index
    }

    fun parse(input: List<String>): Array<Int> {
        val parsedInput = input.first().split(" ").map(String::toInt)
        return Array<Int>(parsedInput.size, parsedInput::get)
    }

    fun calculateMetadataSum(input: List<String>): Int =
        recursiveTreeValue(parse(input), 0, ::metadataValue).first

    fun calculateMetadataChildrenValue(input: List<String>): Int =
        recursiveTreeValue(parse(input), 0, ::childrenValue).first
}
