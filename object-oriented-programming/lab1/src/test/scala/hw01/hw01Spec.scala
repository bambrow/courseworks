package hw01

import org.scalactic.Tolerance._
import org.scalatest.FlatSpec
import hw01._

class hw01Spec extends FlatSpec {

  "pascal" should "compute the entries in Pascal's triangle" in {
    assert(pascal(0, 0) === 1)
    assert(pascal(0, 1) === 0)
    assert(pascal(0, -1) === 0)
    assert(pascal(1, 0) === 1)
    assert(pascal(1, 1) === 1)
    assert(pascal(2, 0) === 1)
    assert(pascal(2, 1) === 2)
    assert(pascal(2, 2) === 1)
    assert(pascal(5, 2) === 10)
  }

  "cubicRootStep" should "evaluate to one iteration of Newton's method" in {
    assert(cubicRootStep(8, 1) === 3.3333333333333335 +- 0.00000001)
    assert(cubicRootStep(1, 1) === 1.0)
    assert(cubicRootStep(9, 8) === 5.380208333333333 +- 0.00000001)
  }

  "cubicRootN" should "perform several iterations of sqrtStep" in {
    assert(cubicRootN(8,1,2) === 2.462222222222222 +- 0.00000001)
    assert(cubicRootN(8,1,6) === 2.0 +- 0.00000001)
    assert(cubicRootN(8,20,2) === 8.90831834457584 +- 0.00000001)
    assert(cubicRootN(8,20,6) === 2.2354663313407612 +- 0.00000001)
  }

  "cubicRootN" should "evaluate to x0 if n is zero" in {
    assert(cubicRootN(8,1,0) === 1.0)
    assert(cubicRootN(8,20,0) === 20.0)
  }

  "cubicRootN" should "return same results as cubicRootNLoop" in {
    assert(cubicRootN(8,1,2) === cubicRootNLoop(8,1,2) +- 0.00000001)
    assert(cubicRootN(8,1,6) === cubicRootNLoop(8,1,6) +- 0.00000001)
    assert(cubicRootN(8,20,2) === cubicRootNLoop(8,20,2) +- 0.00000001)
    assert(cubicRootN(8,20,6) === cubicRootNLoop(8,20,6) +- 0.00000001)
    assert(cubicRootN(8,1,0) === cubicRootNLoop(8,1,0))
    assert(cubicRootN(8,20,0) === cubicRootNLoop(8,20,0))
  }

  "cubicRootErr" should "perform iterations until the error is within epsilon" in {
    assert(cubicRootErr(8, 1, 0.1) === 2.0 +- 0.1)
    assert(cubicRootErr(8, 1, 0.0001) === 2.0 +- 0.0001)
    assert(cubicRootErr(8, 1, 0.00000001) === 2.0 +- 0.00000001)
  }

  "cubicRootErr" should "return same results as cubicRootErrLoop" in {
    assert(cubicRootErr(8, 1, 0.1) === cubicRootErrLoop(8, 1, 0.1) +- 0.1)
    assert(cubicRootErr(8, 1, 0.0001) === cubicRootErrLoop(8, 1, 0.0001) +- 0.0001)
    assert(cubicRootErr(8, 1, 0.00000001) === cubicRootErrLoop(8, 1, 0.00000001) +- 0.00000001)
  }

  "cubicRoot" should "compute the cubic root" in {
    assert(cubicRoot(8) === 2.0 +- 0.0001)
    assert(cubicRoot(27) === 3.0 +- 0.0001)
    assert(cubicRoot(64) === 4.0 +- 0.0001)
  }

  "cubicRootN" should "throw an exception when n is negative" in {
    intercept[java.lang.IllegalArgumentException] {
      cubicRootN(8, 1, -10)
    }
  }

  "cubicRootNLoop" should "throw an exception when n is negative" in {
    intercept[java.lang.IllegalArgumentException] {
      cubicRootNLoop(8, 1, -10)
    }
  }

  "cubicRootErr" should "throw an exception when using a non-positive epsilon" in {
    intercept[java.lang.IllegalArgumentException] {
      cubicRootErr(4, 1, -0.01)
    }
    intercept[java.lang.IllegalArgumentException] {
      cubicRootErr(4, 1, 0.0)
    }
  }

  "cubicRootErrLoop" should "throw an exception when using a non-positive epsilon" in {
    intercept[java.lang.IllegalArgumentException] {
      cubicRootErrLoop(4, 1, -0.01)
    }
    intercept[java.lang.IllegalArgumentException] {
      cubicRootErrLoop(4, 1, 0.0)
    }
  }

  val t1 = Node(Empty, 2, Empty)
  val t2 = Node(t1, 4, Empty)
  val t3 = Node(Empty, 4, t2)
  val t4 = Node(t1, 4, Node(Empty, 4, Node(Empty, 5, Empty)))
  val t5 = Node(Empty, 2, t1)
  val t6 = Node(Empty, 4, Empty)
  val t7 = Node(Empty, 2, Node(Empty, 4, Empty))
  val t8 = Node(Node(Empty, 1, Empty), 2, Node(Empty, 3, Empty))
  val t9 = Node(Empty, 2, Node(Empty, 3, Empty))
  val t10 = Node(Node(Empty, 1, Empty), 2, Empty)
  val t11 = Node(Node(Empty, 1, Empty), 3, Empty)

  "repOk" should "ensure that a BSTree is properly ordered" in {
    assert(repOk(Empty))
    assert(repOk(t1))
    assert(repOk(t2))
    assert(!repOk(t3))
    assert(repOk(t4))
    assert(repOk(t5))
  }

  "insert" should "insert numbers as leaves in BSTrees at the proper position" in {
    assert(insert(Empty, 2) === t1)
    assert(insert(t1, 2) === t5)
    assert(insert(t6, 2) === t2)
    assert(insert(t1, 4) === t7)
  }

  "deleteMin" should "remove the smallest value from a tree, and provide the resulting tree" in {
    assert(deleteMin(t7) === (t6, 2))
    assert(deleteMin(t1) === (Empty, 2))
    assert(deleteMin(t5) === (t1, 2))
    assert(deleteMin(t4) === (Node(Empty, 4, Node(Empty, 4, Node(Empty, 5, Empty))), 2))
  }

  "delete" should "remove a given value from a tree, if present" in {
    assert(delete(t1, 2) === (Empty, true))
    assert(delete(t5, 2) === (t1, true))
    assert(delete(t8, 1) === (t9, true))
    assert(delete(t8, 3) === (t10, true))
    assert(delete(t8, 2) === (t11, true))
    assert(delete(t8, 4) === (t8, false))
  }

  def treeFromList(l: List[Int]): BSTree = ((Empty: BSTree) /: l)(insert)

  "insert" should "produce trees that satisfy repOk" in {
    assert(repOk(treeFromList(List(3, 4, 7, 2, 1, 10))))
    assert(repOk(treeFromList(List(1, 2, 3, 4, 5))))
    assert(repOk(treeFromList(List(9, 8, 7, 6, 5))))
    assert(repOk(treeFromList(List(1, 1, 1, 1))))
  }

  "insert-delete" should "produce tress that satisfy repOk" in {
    val ins = (n: Int) => (t: BSTree) => insert(t,n)
    val del = (n: Int) => (t: BSTree) => delete(t,n)._1
    ((Empty: BSTree) /: List(ins(2), ins(6), ins(10), ins(22), del(4), del(6), ins(4), del(10), del(4)))(
        (t, f) => {
          val t1 = f(t)
          assert(repOk(t1))
          t1
        }
    )
  }

}
