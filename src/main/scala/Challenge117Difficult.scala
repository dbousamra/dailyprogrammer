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
    }
}
object Challenge117Difficult2 extends JavaTokenParsers {
  def expr: Parser[Any] = primitives ~ rep(operators)
  def operators: Parser[Any] = "*"~expr | "/"~expr | "+"~expr | "-"~expr
  def primitives = "x" | "y" | "z" | "("~expr~")"

  def parse(input: String) = parseAll(expr, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => failure
  }
  def main(args: Array[String]): Unit = {
    val parsed = parse("( x + y ) * x - z * y / ( x + x )")
    val parsed2 = parse("(xx - zz + x / z)")
    val parsed3 = parse("x + y * x - z * y / x + x")
    println(parsed3)
  }
}

abstract case class Rule(product: String, expression: String)
case class Primitive(override val product: String, override val expression: String) extends Rule(product, expression)
case class BinaryExpression (override val product: String, override val expression: String) extends Rule(product, expression)

object Rule {
  def apply(line: String) = {
    val splitted = line.split("=")
    val (product, expression) = (splitted(0), splitted(1))
    product.r.findAllIn(expression).length match {
      case 0 | 1 => Primitive(product, expression)
      case _ => BinaryExpression(product, expression)
    }   
  }
}

object Challenge117Difficult3 extends JavaTokenParsers {

  val input = """S = x 
S = y 
S = z 
S = S + S 
S = S - S 
S = S * S 
S = S / S 
S = ( S ) """

  def parseToRules(cfg: String) = cfg.replaceAll(" ", "").split("\n").map(Rule(_))

  def main(args: Array[String]): Unit = {
    val rules = parseToRules(input)
    val (primitives, binaries) = rules.partition{ case x: Primitive => true; case _ => false }
    println(primitives.mkString("\n"))

    val primitives2 = primitives.foldRight(failure("Non valid rule"): Parser[Any])(_.expression|_)

    println(parse("x", primitives2))
  }

  def parse(input: String, expr: Parser[Any]) = parseAll(expr, input) match {
    case Success(result, _) => result
    case failure: NoSuccess => failure
  }
}