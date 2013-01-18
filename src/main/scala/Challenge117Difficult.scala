import scala.util.parsing.combinator._
import scala.util.parsing.input._

object Challenge117Difficult extends JavaTokenParsers {
    def expr = rep(term)
    def term: Parser[Any] = "()" | "("~expr~")"

    def parse(input: String) = parseAll(expr, input) match {
      case Success(result, _) => result
      case failure: NoSuccess => failure
    }
    def main(args: Array[String]): Unit = {
      val parsed = parse("(()()(((()))))")
      println(parsed)
    }
}