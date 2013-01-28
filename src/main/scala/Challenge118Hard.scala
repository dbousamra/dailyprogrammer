import io.Source
import java.io.File
import util.Random

object Challenge118Hard {

  lazy val words = Source.fromFile(new File("src/main/resources/cipherWords.txt")).getLines().toList

  def main(args: Array[String]) {
    val bestRandom = (0 to 100).map(x => getRandomCipher).maxBy(cipherScore)
    var bestCipherSoFar = bestRandom
    (0 to 50).foreach { i =>
      val currentScore = cipherScore(bestCipherSoFar)
      println("BEST SO FAR = " + bestCipherSoFar + " with score: " + currentScore)
      val mutatedCiphers = mutateCipher(bestCipherSoFar).maxBy(cipherScore)
      bestCipherSoFar = mutatedCiphers
    }
  }

  def getRandomCipher = Random.shuffle(('a' to 'z').toList).mkString("")

  def cipherScore(cipher: String) = words.map(testWord(cipher, _)).count(_ == true)

  def testWord(cipher: String, word: String) = {
    val subs = ('a' to 'z').zip(cipher).toMap
    val replaced = word.map(c => subs.get(c).get)
    replaced.sorted == replaced
  }

  def mutateCipher(cipher: String) = {
    for {
      c1 <- ('a' to 'z'); c2 <- ('a' to 'z')
    } yield swap(cipher, cipher.indexOf(c1), cipher.indexOf(c2))
  }

  def swap(cipher : String, first : Int, second : Int) : String = {
    val cs = cipher.toCharArray
    val swp = cs(first)
    cs(first) = cs(second)
    cs(second) = swp
    new String(cs)
  }
}
