import scala.util.Random._

object Challenge116 {
  def main(args: Array[String]): Unit = {
    val (steps, leftChance, rightChance) = (10, 0.5, 0.4)
    println(calculateRightRights(4))
  }

  def walk(stepsRem: Int, chanceLeft: Int, chanceRight: Int, distanceRight: Double): Double = {
    if (stepsRem == 0) {
      distanceRight
    } else {
      val justWalked = nextDouble
      walk(stepsRem - 1, chanceLeft, chanceRight, distanceRight + justWalked)
    }
  }

  def calculateDirection(chanceLeft: Int, chanceRight: Int): Double = {
    1.0
  }

  def calculateLeftRights(n: Int): Int = n match {
    case 1 | 2 => 0
    case 3 => 1
    case x => 1 + (calculateLeftRights(n - 1) * 2)
  }

  def calculateRightRights(x: Int): Double = (1 + x)/(1 - (2 * x) - math.pow(x, 2) + math.pow(x, 3))
}

