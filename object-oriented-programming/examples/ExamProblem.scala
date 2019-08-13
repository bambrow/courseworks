package exam

object ExamProblem {

  abstract class Tree[T,V]

  case class Node[T,V](l: Tree[T,V], r: Tree[T,V]) extends Tree[T,V]

  case class Leaf[T,V](f: T => V) extends Tree[T,V]

  def fringe[T,V](t: Tree[T,V], ele: T): List[V] = t match {
    case Leaf(f) => List(f(ele))
    case Node(l,r) => fringe(l,ele) ++ fringe(r,ele)
  }

  def intToString(x: Int): String = x.toString

  def main(args: Array[String]): Unit = {

    val myTree: Tree[Int,String] = Node(Leaf((x: Int) => x.toString), Leaf((x: Int) => x.toString))
    println(fringe(myTree, 3))

  }

}
