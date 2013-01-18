import net.liftweb.json._

object Challenge117 {
  case class RawPost(title: String, permalink: String)
  case class Post(difficulty: String, id: String, title: String, permalink: String) {
    override def toString = "[" + difficulty + "]" + " #" + id + ": " + "\"" + title + "\"" + " " + permalink
  }
 
  val dailyJson = io.Source.fromURL("http://www.reddit.com/r/dailyprogrammer/.json?limit=30").mkString
  implicit val formats = DefaultFormats
  val mapped = (parse(dailyJson) \ "data" \ "children" \ "data")
               .extract[List[RawPost]]
               .filterNot(_.title.contains("MOD POST"))
  val difficulties = List("Easy", "Intermediate", "Difficult")
  val posts = mapped.map { post =>
    val splitted = post.title.split("Challenge #").tail.mkString
    val id = splitted.split(" ").head
    val difficulty = difficulties.collectFirst { case(d) if splitted contains(d) => d }.getOrElse("None")
    val title = splitted.split("""\[""" + difficulty + """\] """).last
    Post(difficulty, id, title, post.permalink)
  }
  def main(args: Array[String]): Unit = {
    val listOne = posts.sortBy(_.id).mkString("\n")
    val listTwo = posts.sortBy(_.difficulty).mkString("\n")
    println(listOne)
    println()
    println(listTwo)
  }
}
