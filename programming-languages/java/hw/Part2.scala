import scala.collection.mutable.ListBuffer

abstract class Tree[+T]

case class Node[+T](v: T, l: Tree[T], r: Tree[T]) extends Tree[T]

case class Leaf[+T](v: T) extends Tree[T]

trait Addable[T] {
  def +(other: T): T
}

class A(a: Int) extends Addable[A] {
  val value = a
  def +(other: A) = new A(this.value + other.value)
  override def toString = "A(" + value + ")"
}

class B(b: Int) extends A(b) {
  override def toString = "B(" + value + ")"
}

class C(c: Int) extends B(c) {
  override def toString = "C(" + value + ")"
}

object Part2 {
  
  def inOrder[T](t: Tree[T]): List[T] = {
    val lb = ListBuffer[T]()
    t match {
      case Node(v,l,r) => {
        lb ++= inOrder(l)
        lb += v
        lb ++= inOrder(r)
      }
      case Leaf(v) => lb += v
    }
    lb.toList
  }
  
  def treeSum[T <: Addable[T]](t: Tree[T]): T = {
    t match {
      case Node(v,l,r) => treeSum(l) + v + treeSum(r)
      case Leaf(v) => v
    }
  }
  
  def treeMap[T, V](f: T => V, t: Tree[T]): Tree[V] = {
    t match {
      case Node(v,l,r) => new Node(f(v), treeMap(f, l), treeMap(f, r))
      case Leaf(v) => new Leaf(f(v))
    }
  }
  
  def BTreeMap(f: B => B, t: Tree[B]): Tree[B] = {
    t match {
      case Node(v,l,r) => new Node(f(v), BTreeMap(f, l), BTreeMap(f, r))
      case Leaf(v) => new Leaf(f(v))
    }
  }
  
  def test() {
	      def faa(a:A):A = new A(a.value+10)
			  def fab(a:A):B = new B(a.value+20)
			  def fba(b:B):A = new A(b.value+30)
			  def fbb(b:B):B = new B(b.value+40)
			  def fbc(b:B):C = new C(b.value+50)
			  def fcb(c:C):B = new B(c.value+60)
			  def fcc(c:C):C = new C(c.value+70)
			  def fac(a:A):C = new C(a.value+80)
			  def fca(c:C):A = new A(c.value+90)

			  val myBTree: Tree[B] = Node(new B(4),Node(new B(2),Leaf(new B(1)),Leaf(new B(3))), 
					  Node(new B(6), Leaf(new B(5)), Leaf(new B(7))))

			  val myATree: Tree[A] = myBTree

			  println("inOrder = " + inOrder(myATree))
			  println("Sum = " + treeSum(myATree))
			  
			  /*
			   *        Given A--B--C, and follow the rules that
			   *        function subtyping is contravariant in the 
			   *        input types and is covariant in the output types,
			   *        we have:
			   *              
			   *               C -> A
			   *                /  \
			   *               /    \
			   *              /      \
			   *          B -> A    C -> B
			   *           /  \      /  \
			   *          /    \    /    \
			   *         /      \  /      \
			   *     A -> A    B -> B    C -> C
			   *         \      /  \      /
			   *          \    /    \    /
			   *           \  /      \  /
			   *          A -> B    B -> C
			   *              \      /
			   *               \    /
			   *                \  /
			   *               A -> C
			   *                     
			   */
			  
			  // BTreeMap returns type Tree[B], and the input function must be B -> B.

			  // println(BTreeMap(faa,myBTree))
			  // Type error. Use the graph above, we know that A -> A is not the subtype of B -> B.
			  
			  println(BTreeMap(fab,myBTree))
			  
			  // println(BTreeMap(fba,myBTree))
			  // Type error. Use the graph above, we know that B -> A is not the subtype of B -> B, but the supertype.
			  
			  println(BTreeMap(fbb,myBTree))
			  
			  println(BTreeMap(fbc,myBTree))
			  
			  // println(BTreeMap(fcb,myBTree))
			  // Type error. Use the graph above, we know that C -> B is not the subtype of B -> B, but the supertype.
			  
			  // println(BTreeMap(fcc,myBTree))
			  // Type error. Use the graph above, we know that C -> C is not the subtype of B -> B.
			  
			  println(BTreeMap(fac,myBTree))
			  
			  // println(BTreeMap(fca,myBTree))
			  // Type error. Use the graph above, we know that C -> A is not the subtype of B -> B, but the supertype.
			  
			  
			  // treeMap accepts input tree as Tree[T]. In the below cases, type A is the type T.
			  // So treeMap below accepts the input function as A -> V.
			  // We can still use the graph above.

			  println(treeMap(faa,myATree))
			  
			  println(treeMap(fab,myATree))
			  
			  // println(treeMap(fba,myATree))
			  // Type error. Because the function subtyping is contravariant in the input types, 
			  // we know that B -> V is not the subtype of A -> V (in this case V is A), but the supertype.
			  
			  // println(treeMap(fbb,myATree))
			  // Type error. Because the function subtyping is contravariant in the input types, 
			  // we know that B -> V is not the subtype of A -> V (in this case V is B), but the supertype.
			  
			  // println(treeMap(fbc,myATree))
			  // Type error. Because the function subtyping is contravariant in the input types, 
			  // we know that B -> V is not the subtype of A -> V (in this case V is C), but the supertype.
			  
			  // println(treeMap(fcb,myATree))
			  // Type error. Because the function subtyping is contravariant in the input types, 
			  // we know that C -> V is not the subtype of A -> V (in this case V is B), but the supertype.
			  
			  // println(treeMap(fcc,myATree))
			  // Type error. Because the function subtyping is contravariant in the input types, 
			  // we know that C -> V is not the subtype of A -> V (in this case V is C), but the supertype.
			  
			  println(treeMap(fac,myATree))
			  
			  // println(treeMap(fca,myATree))
			  // Type error. Because the function subtyping is contravariant in the input types, 
			  // we know that C -> V is not the subtype of A -> V (in this case V is A), but the supertype.
			  
  }
  
  def main(args: Array[String]): Unit = {
    test()
  }
  
}