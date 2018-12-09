package aoc2018

import java.math.BigInteger

object Exercise09 {
    fun highscore(players: Int, lastMarble: Int): BigInteger {
        val list = CircularLinkedList(0.toBigInteger())
        var score = Array(players, { 0.toBigInteger() })
        var playerTurn = 0

        for (num in 1 .. lastMarble) {
            if (num % 23 == 0) {
                score[playerTurn] = score[playerTurn] + num.toBigInteger()
                list.back(7)
                score[playerTurn] += list.remove()
            } else {
                list.add(num.toBigInteger())
            }

            playerTurn = (playerTurn + 1) % players
        }

        return score.max()!!
    }

    fun parse(line: String): Pair<Int, Int> {
        val regex = Regex("(\\d+) players; last marble is worth (\\d+) points")
        val (players, lastMarble) = regex.matchEntire(line)!!.destructured
        return players.toInt() to lastMarble.toInt()
    }

    fun gameHighscore(input: List<String>): BigInteger {
        val (players, lastMarble) = parse(input.first())
        return highscore(players, lastMarble)
    }

    fun gameHighscorex100(input: List<String>): BigInteger {
        val (players, lastMarble) = parse(input.first())
        return highscore(players, lastMarble * 100)
    }
}
