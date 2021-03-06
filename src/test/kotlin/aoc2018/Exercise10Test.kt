package aoc2018

import kotlin.test.*

class Exercise10Test {
  val vectors = listOf(
      Vect2D(9, 1, 0, 2), Vect2D(7, 0, -1, 0), Vect2D(3, -2, -1, 1),
      Vect2D(6, 10, -2, -1), Vect2D(2, -4, 2, 2), Vect2D(-6, 10, 2, -2),
      Vect2D(1, 8, 1, -1), Vect2D(1, 7, 1, 0), Vect2D(-3, 11, 1, -2),
      Vect2D(7, 6, -1, -1), Vect2D(-2, 3, 1, 0), Vect2D(-4, 3, 2, 0),
      Vect2D(10, -3, -1, 1), Vect2D(5, 11, 1, -2), Vect2D(4, 7, 0, -1),
      Vect2D(8, -2, 0, 1), Vect2D(15, 0, -2, 0), Vect2D(1, 6, 1, 0),
      Vect2D(8, 9, 0, -1), Vect2D(3, 3, -1, 1), Vect2D(0, 5, 0, -1),
      Vect2D(-2, 2, 2, 0), Vect2D(5, -2, 1, 2), Vect2D(1, 4, 2, 1),
      Vect2D(-2, 7, 2, -2), Vect2D(3, 6, -1, -1), Vect2D(5, 0, 1, 0),
      Vect2D(-6, 0, 2, 0), Vect2D(5, 9, 1, -2), Vect2D(14, 7, -2, 0),
      Vect2D(-3, 6, 2, -1))

      @Test fun test() {
          val result = """
              ........#.............
              ................#.....
              .........#.#..#.......
              ......................
              #..........#.#.......#
              ...............#......
              ....#.................
              ..#.#....#............
              .......#..............
              ......#...............
              ...#...#.#...#........
              ....#..#..#.........#.
              .......#..............
              ...........#..#.......
              #...........#.........
              ...#.......#..........
          """.trimIndent()

          val dimensions = Exercise10.calculateDimensions(vectors)
          assertEquals(result, Exercise10.printPositions(vectors, dimensions))
      }

      @Test fun testStep1() {
          val result = """
              ......................
              ......................
              ..........#....#......
              ........#.....#.......
              ..#.........#......#..
              ......................
              ......#...............
              ....##.........#......
              ......#.#.............
              .....##.##..#.........
              ........#.#...........
              ........#...#.....#...
              ..#...........#.......
              ....#.....#.#.........
              ......................
              ......................
          """.trimIndent()

          val dimensions = Exercise10.calculateDimensions(vectors)
          assertEquals(result, Exercise10.printPositions(Exercise10.simulationStep(vectors), dimensions))
      }

      @Test fun testStep2() {
          val result = """
              ......................
              ......................
              ......................
              ..............#.......
              ....#..#...####..#....
              ......................
              ........#....#........
              ......#.#.............
              .......#...#..........
              .......#..#..#.#......
              ....#....#.#..........
              .....#...#...##.#.....
              ........#.............
              ......................
              ......................
              ......................
          """.trimIndent()

          val dimensions = Exercise10.calculateDimensions(vectors)
          assertEquals(result, Exercise10.printPositions(Exercise10.simulationStep(Exercise10.simulationStep(vectors)), dimensions))
      }

}
