import org.joda.time._

object MayanTime {
  sealed trait Time 
  case class Kin(n: Int) extends Time
  case class Uinal(n: Int) extends Time
  case class Tun(n: Int) extends Time
  case class Katun(n: Int) extends Time
  case class Baktun(n: Int) extends Time

  implicit def int2MayanBuilder(n: DateTime) = new {
    def asMayan = {
      val start = new DateTime(1970, 1, 1, 0, 0, 0, 0)
      val duration = new Duration(start, n)
      MayanBuilder(Kin(duration.getStandardDays().toInt + 1856305))
    }
  }

  implicit def int2RichInt(n: Int) = new {
    def kin = MayanBuilder(Kin(n))
    def uinal = MayanBuilder(Uinal(n))
    def tun = MayanBuilder(Tun(n))
    def katun = MayanBuilder(Katun(n))
    def baktun = MayanBuilder(Baktun(n))
  }

  case class MayanBuilder(time: Time) {
    def kin = time match {
      case Kin(n)    => n * 1
      case Uinal(n)  => n * 1 * 20
      case Tun(n)    => n * 1 * 20 * 18
      case Katun(n)  => n * 1 * 20 * 18 * 20
      case Baktun(n) => n * 1 * 20 * 18 * 20 * 20
    }


    def uinal = (this - katun.katun - baktun.baktun - tun.tun).kin / 1.uinal.kin

    def tun = (this - katun.katun - baktun.baktun).kin / 1.tun.kin

    def katun = (this - baktun.baktun).kin / 1.katun.kin

    def baktun = kin / 1.baktun.kin

    def asDateTime = new DateTime(asMilliseconds)

    def + (db: MayanBuilder) = MayanBuilder { Kin(this.kin + db.kin) }

    def - (db: MayanBuilder) = MayanBuilder { Kin(this.kin - db.kin) }

    override def toString = baktun + "." + katun + "." + tun + "." + uinal + "." + remainingKin

    private def remainingKin = (this - katun.katun - baktun.baktun - tun.tun - uinal.uinal).kin / 1.kin.kin

    private def asMilliseconds: Long = (kin - 1856305) * 86400000L
  }

}


object Challenge117Intermediate {
  import MayanTime._
  def main(args: Array[String]): Unit = {
    // println(12.baktun + 17.katun + 16.tun + 7.uinal + 5.kin - 22.kin)
    println((14.baktun + 0.katun + 0.tun + 0.uinal + 0.kin).asDateTime)
  }
}