package exam

object DropUntil {

  def dropUntil[A](l: List[A])(f: A => Boolean): List[A] = l match {
    case Nil => Nil
    case x :: xs => {
      if (!f(x)) dropUntil(xs)(f)
      else l
    }
  }

  def dropUntil2[A](l: List[A])(f: A => Boolean): List[A] = {
    l.foldLeft((Nil: List[A], true)) {
      case ((acc, flag), x) => if (!f(x) && flag) (acc, flag) else (x :: acc, false)
    }._1 reverse
  }

  def main(args: Array[String]): Unit = {
    val a: List[Int] = List(-1, -2, 1, -3, 2, 5)
    println(dropUntil(a)(_ > 0))
    println(dropUntil2(a)(_ > 0))
  }

}
