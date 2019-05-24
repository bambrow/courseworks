package hw04

import scala.collection.immutable.AbstractMap
import scala.language.implicitConversions

object hw04 {
  
  abstract class TreeMap[K <% Ordered[K], +V] extends AbstractMap[K, V] {

    override def +[V1 >: V](key: (K, V1)): TreeMap[K, V1] = this match {
      case Empty() => Node(Empty(), Empty(), key._1, key._2)
      case Node(left, right, k, v) => {
        if (key._1 == k) Node(left, right, k, key._2)
        else if (key._1 < k) Node((left: TreeMap[K, V1]) + key, right, k, v)
        else Node(left, (right: TreeMap[K, V1]) + key, k, v)
      }
    }

    override def -(key: K): TreeMap[K, V] = {
      def deleteMin(map: TreeMap[K, V]): (TreeMap[K, V], K, V) = {
        (map: @unchecked) match {
          case Node(Empty(), right, k, v) => (right, k, v)
          case Node(left, right, k, v) => {
            val (newLeft, k1, v1) = deleteMin(left)
            (Node(newLeft, right, k, v), k1, v1)
          }
        }
      }
      this match {
        case Empty() => this
        case Node(left, Empty(), k, v) => {
          if (key == k) left
          else if (key < k) Node(left - key, Empty(), k, v)
          else this
        }
        case Node(left, right, k, v) => {
          if (key == k) {
            val (newRight, k1, v1) = deleteMin(right)
            Node(left, newRight, k1, v1)
          }
          else if (key < k) Node(left - key, right, k, v)
          else Node(left, right - key, k, v)
        }
      }
    }

    override def get(key: K): Option[V] = this match {
      case Empty() => None
      case Node(left, right, k, v) => {
        if (key == k) Some(v)
        else if (key < k) left.get(key)
        else right.get(key)
      }
    }

    override def iterator: Iterator[(K, V)] = {
      def fold[A](map: TreeMap[K, V])(init: A)(f: (A, K, V) => A): A = map match {
        case Empty() => init
        case Node(left, right, k, v) => fold(right)(f(fold(left)(init)(f), k, v))(f)
      }
      fold(this)(Nil: List[(K, V)])((l, k, v) => l :+ (k, v)).iterator
    }

  }
  
  case class Empty[K <% Ordered[K]]() extends TreeMap[K, Nothing]
  case class Node[K <% Ordered[K], +V](left: TreeMap[K, V], right: TreeMap[K, V],
                         key: K, value: V) extends TreeMap[K, V]

  object TreeMap {
    def apply[K <% Ordered[K], V](kvs: (K, V)*): Map[K, V] =
      kvs.toSeq.foldLeft(Empty[K](): Map[K, V])(_ + _)
  }
      
  trait Monoid[A] {
    def combine(x: A, y: A): A
    val zero: A
  }

  object Monoid {
    implicit def product[A: Monoid, B: Monoid] = new Monoid[(A, B)] {
      import MonoidOps._
  
      override def combine(x: (A, B), y: (A, B)) = (x._1 |+| y._1, x._2 |+| y._2)
      override val zero = (mzero[A], mzero[B])
    }
  }

  class MonoidOps[A](v: A)(implicit m: Monoid[A]) {
    def |+|(that: A): A = m.combine(v, that)
  }

  object MonoidOps {
    implicit def toMonoidOps[T: Monoid](v: T): MonoidOps[T] = new MonoidOps[T](v)
    def mzero[T](implicit m: Monoid[T]): T = m.zero
  }

  def mapConcatVec[A: Monoid, B](as: IndexedSeq[B])(f: B => A): A = {
    import MonoidOps._
    if (as.length == 0) mzero[A]
    else if (as.length == 1) f(as(0))
    else {
      val (l, r) = as.splitAt(as.length / 2)
      mapConcatVec(l)(f) |+| mapConcatVec(r)(f)
    }
  }

  sealed trait WordCount
  case class Stub(chars: String) extends WordCount
  case class Part(lStub: String, words: Int, rStub: String) extends WordCount
  
  implicit val wcMonoid: Monoid[WordCount] = new Monoid[WordCount] {
    override def combine(x: WordCount, y: WordCount) = (x, y) match {
      case (Stub(p), Stub(q)) => Stub(p + q)
      case (Stub(p), Part(l, w, r)) => Part(p + l, w, r)
      case (Part(l, w, r), Stub(p)) => Part(l, w, r + p)
      case (Part(l1, w1, r1), Part(l2, w2, r2)) => Part(l1, w1 + w2 + (if ((r1 + l2).length > 0) 1 else 0), r2)
    }
    override val zero = Stub("")
  }
  
  def count(s: String): Int = {
    def handleChar(c: Char): WordCount = {
      if (c.isWhitespace) Part("", 0, "")
      else Stub(c.toString)
    }
    mapConcatVec(s.toIndexedSeq)(handleChar) match {
      case Stub(p) => Math.min(p.length, 1)
      case Part(l, w, r) => Math.min(l.length, 1) + w + Math.min(r.length, 1)
    }
  }
}
