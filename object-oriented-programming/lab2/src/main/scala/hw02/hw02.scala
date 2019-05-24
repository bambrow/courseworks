package hw02

import scala.language.postfixOps

object hw02 {

  class Set(val rep: Int => Boolean) {
    def contains(x: Int): Boolean = rep(x)

    def insert(x: Int): Set = Set((y: Int) => (rep(y) || y == x))

    def filter(p: Int => Boolean): Set = Set((y: Int) => (rep(y) && p(y)))

    def union(other: Set): Set = Set((y: Int) => (this.contains(y) || other.contains(y)))

    def intersect(other: Set): Set = Set((y: Int) => (this.contains(y) && other.contains(y)))
  }

  object Set {
    def apply(rep: Int => Boolean): Set = new Set(rep)

    def apply(x: Int): Set = Set((y: Int) => y == x)

    def empty: Set = Set((_: Int) => false)
  }

  sealed abstract class BSTree {

    def insert(n: Int): BSTree = this match {
      case Empty => Node(Empty, n, Empty)
      case Node(left, data, right) => {
        if (n < data) Node(left.insert(n), data, right)
        else Node(left, data, right.insert(n))
      }
    }

    def fold[A](init: A)(f: (A, Int) => A): A = this match {
      case Empty => init
      case Node(left, data, right) =>
        right.fold(f(left.fold(init)(f), data))(f)
    }

    def sum: Int = this.fold(0)(_ + _)

    def strictlySorted: Boolean = this.fold((Integer.MIN_VALUE, true))((t, d) => (d, t._2 && t._1 < d))._2

    def toList: List[Int] = this.fold(Nil: List[Int])((l, d) => l :+ d)
  }

  case object Empty extends BSTree
  case class Node(left: BSTree, data: Int, right: BSTree) extends BSTree

  def unlines(lines: List[List[Char]]): List[Char] = lines match {
    case List() => List()
    case _ => lines reduce((a, b) => a ::: ('\n' :: b))
  }

  def firstOfJan(y: Int): Int = {
    val x = y - 1
    (365*x + x/4 - x/100 + x/400 + 1) % 7
  }

  def isLeapYear(y: Int) =
    if (y % 100 == 0) (y % 400 == 0) else (y % 4 == 0)

  def mlengths(y: Int): List[Int] = {
    val feb = if (isLeapYear(y)) 29 else 28
    List(31, feb, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31)
  }

  def firstDay(m: Int, y: Int): Int =
    if (m == 1) firstOfJan(y) else (firstOfJan(y) + mlengths(y).take(m-1).sum) % 7


  case class Picture(height: Int, width: Int, pxx: List[List[Char]]) {
    override def toString: String = unlines(pxx).mkString

    def above(q: Picture) = {
      require(this.width == q.width)
      Picture(this.height + q.height, width, this.pxx ::: q.pxx)
    }

    def beside(q: Picture) = {
      require(this.height == q.height)
      Picture(height, this.width + q.width, this.pxx zip q.pxx map(t => t._1 ::: t._2))
    }
  }

  def pixel(c: Char) = Picture(1, 1, List(List(c)))

  def stack(pics: List[Picture]): Picture = pics reduce(_ above _)

  def spread(pics: List[Picture]): Picture = pics reduce(_ beside _)

  def tile(pxx: List[List[Picture]]): Picture = stack(pxx map spread)

  def rightJustify(w: Int)(chars: List[Char]): Picture = {
    require(chars.length <= w)
    Picture(1, w, List((List[Char]() padTo(w - chars.length, ' ')) ::: chars))
  }

  def group[T](n: Int, xs: List[T]): List[List[T]] = {
    require(n > 0)
    if (xs.length <= n) List(xs) else xs.splitAt(n)._1 :: group(n, xs.splitAt(n)._2)
  }

  def dayPics(d: Int, s: Int): List[Picture] = {
    val t = 42 - d - s
    val l = (1 to s toList).map(x => x.toString).map(x => x.toList).map(x => if (x.length == 1) pixel(x.head) else pixel(x.head) beside pixel(x.last))
    (List[Picture]() padTo(d, pixel(' '))) ::: l ::: (List[Picture]() padTo(t, pixel(' ')))
  }

  def calendar(year: Int, month: Int): Picture = {
    val d = firstDay(month, year)
    val s = mlengths(year)(month - 1)
    val head = List("Su", "Mo", "Tu", "We", "Th", "Fr", "Sa") map(x => x.toList) map(x => pixel(x.head) beside pixel(x.last))
    val days = dayPics(d, s)
    val body = if (days(35) == pixel(' ')) (if (days(28) == pixel(' ')) days.take(28) else days.take(35)) else days
    tile(group(7, (head ::: body) map(x => rightJustify(3)(x.pxx.head))))
  }

}
