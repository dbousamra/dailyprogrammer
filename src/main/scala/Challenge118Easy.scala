import java.util._
import java.text._

object Challenge118Easy {

  val input = "%D.%"

  def main(args: Array[String]): Unit = {
    println(formatter(input))
  }

  def formatter(input: String) = {
    val date = new Date
    date.getTime()
  }
}
