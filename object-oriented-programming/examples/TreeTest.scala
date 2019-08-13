package exam

object TreeTest {

  abstract class Tree[+A] {
    def map[B](f: A => B): Tree[B]
  }

  case class Leaf[A](x: A) extends Tree[A] {
    override def toString: String = x.toString

    override def map[B](f: A => B): Tree[B] = Leaf(f(x))
  }

  object Leaf {
    def apply[A](x: A) = new Leaf(x)
  }

  case class Node[A](x: A, ch: List[Tree[A]]) extends Tree[A] {
    override def toString: String = ch match {
      case Nil => x.toString
      case l => "(" + x + ", [" + (l mkString ", ") + "]" + ")"
    }

    override def map[B](f: A => B): Tree[B] = {
      val ch1 = ch.map(_ map f)
      Node[B](f(x), ch1)
    }
  }

  object Node {
    def apply[A](x: A, ch: List[Tree[A]]) =  new Node(x, ch)
  }

  def BFS[A](t: Tree[A]): List[A] = {
    def BFS1[T](q: List[Tree[T]], l: List[T]): List[T] = q match {
      case Nil => l
      case x :: xs => x match {
        case Leaf(v) => BFS1(xs, l ::: List(v))
        case Node(v, ch) => BFS1(xs ::: ch, l ::: List(v))
      }
    }
    BFS1(t :: Nil, List[A]())
  }

  def main(args: Array[String]): Unit = {
    val t2: Tree[Int] = Node(2, List(Leaf(6), Leaf(7), Leaf(8)))
    val t3: Tree[Int] = Node(3, List(Leaf(9)))
    val t4: Tree[Int] = Node(4, List(Leaf(10), Leaf(11)))
    val t1: Tree[Int] = Node(1, List(t2, t3, t4, Leaf(5)))
    println(t1)
    println(BFS(t1))
  }

}
