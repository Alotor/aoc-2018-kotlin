package aoc2018

import java.math.BigInteger

data class Dimensions(
    val fromX: Int,
    val toX: Int,
    val fromY: Int,
    val toY: Int
)
object Exercise10 {

    fun parse(line: String): Vect2D {
        val regex = Regex("position=<\\s*(-?\\d+),\\s*(-?\\d+)> velocity=<\\s*(-?\\d+),\\s*(-?\\d+)>")
        val (positionX, positionY, velocityX, velocityY) = regex.matchEntire(line)!!.destructured
        return Vect2D(positionX.toInt(), positionY.toInt(), velocityX.toInt(), velocityY.toInt())
    }

    fun calculateDimensions(vectors: List<Vect2D>): Dimensions {
        val fromX = vectors.minBy { it.positionX }!!.positionX
        val toX = vectors.maxBy { it.positionX }!!.positionX
        val fromY = vectors.minBy { it.positionY }!!.positionY
        val toY = vectors.maxBy { it.positionY }!!.positionY
        return Dimensions(fromX, toX, fromY, toY)
    }

    fun printPositions(vectors: List<Vect2D>, dimensions: Dimensions): String {
        val (fromX, toX, fromY, toY) = dimensions
        val builder = StringBuilder((toX - fromX) * (toY - fromY))

        (fromY .. toY).forEach { curY ->
            (fromX .. toX).forEach { curX ->
                if (vectors.any { it.positionX == curX &&
                                  it.positionY == curY}) {
                    builder.append("#")
                } else {
                    builder.append(".")
                }
            }
            if (curY != toY) {
                builder.append("\n")
            }
        }
        return builder.toString()
    }

    fun simulationStep(vectors: List<Vect2D>): List<Vect2D> = vectors.map {
        Vect2D(
            it.positionX + it.velocityX,
            it.positionY + it.velocityY,
            it.velocityX,
            it.velocityY
        )
    }

    fun pointsBoxArea(vectors: List<Vect2D>): BigInteger {
        val (fromX, toX, fromY, toY) = calculateDimensions(vectors)
        return (toX.toBigInteger() - fromX.toBigInteger()) *
               (toY.toBigInteger() - fromY.toBigInteger())
    }

    fun calculateMessageAndTime(input: List<String>): Pair<String, Int> {
        var points = input.map(::parse)
        var pointsArea = pointsBoxArea(points)
        var iterations = 0

        while (true) {
            val newPoints = simulationStep(points)
            val newArea = pointsBoxArea(newPoints)

            if (newArea > pointsArea) {
                break
            }
            pointsArea = newArea
            points = newPoints
            iterations++
        }

        val dimensions = calculateDimensions(points)
        return printPositions(points, dimensions) to iterations
    }

    fun calculateLightsMessage(input: List<String>): String {
        return calculateMessageAndTime(input).first
    }

    fun calculateLightsTime(input: List<String>): Int {
        return calculateMessageAndTime(input).second
    }

}
