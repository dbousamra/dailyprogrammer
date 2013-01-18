import java.net.URL

class Challenge112  {

  def isValid(url: String) = try { new URL(url).toURI(); true } catch { case _ => false }

  def parse(url: String) = {
    if (isValid(url)) {
      url.split("\\?").tail.head.split("&").toList .map { p => 
        val s = p.split("=")
        (s.head, s.tail.head)
        }.toMap.foreach { p => println(p._1 + ": \"" + p._2 + "\"")}
      }
      else println("The given URL is invalid")
    }
  }
