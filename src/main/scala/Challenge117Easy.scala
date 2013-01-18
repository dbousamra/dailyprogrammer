import scala.io.Source._
import java.lang._
import java.lang.String._

object Challenge117Easy {
  def main(args: Array[String]): Unit = {
    println(pythonVersion("/Users/domlebo70/Documents/workspace/Scala/workbench/build.sbt"))
  }

  def challenge117Easy(filename: String) = {
    def asHex(value: Byte, padding: Int) = format("%0" + padding + "X", Byte.valueOf(value))
    
    val fileAsHex = fromFile(filename).mkString.getBytes.map(asHex(_, 2))
    
    fileAsHex.grouped(18).zipWithIndex.map {
      case (e, i) => (asHex(i.toByte, 8)  +: e).mkString (" ")
    }.mkString("\n")
  }

  def pythonVersion(filename: String) = {
    fromFile(filename).mkString.getBytes.map(x => format("%02X", Byte.valueOf(x))).mkString(" ")
  }
}
