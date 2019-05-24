package hw01

import scala.annotation.tailrec

object hw01 {

  def pascal(row: Int, column: Int): Int = {
    if (row < 0 || column < 0 || column > row) 0
    else if (column == 0 || column == row) 1
    else pascal(row-1, column-1) + pascal(row-1, column)
  }

  def cubicRootStep(c: Double, xn: Double): Double =
    (c / (xn * xn) + 2 * xn) / 3

  def cubicRootN(c: Double, x0: Double, n: Int): Double = {
    require(n >= 0)
    @tailrec def loop(xk: Double, k: Double): Double = {
      if (k > 0) loop(cubicRootStep(c, xk), k-1) else xk
    }
    loop(x0, n)
  }

  def cubicRootNLoop(c: Double, x0: Double, n: Int): Double = {
    require(n >= 0)
    var xk = x0
    var k = 0
    while (k < n) {
      xk = cubicRootStep(c, xk)
      k = k + 1
    }
    xk
  }

  def cubicRootErr(c: Double, x0: Double, epsilon: Double): Double = {
    require(epsilon > 0)
    @tailrec def loop(xk: Double): Double = {
      if (math.abs(math.pow(xk, 3) - c) < epsilon) xk else loop(cubicRootStep(c, xk))
    }
    loop(x0)
  }

  def cubicRootErrLoop(c: Double, x0: Double, epsilon: Double): Double = {
    require(epsilon > 0)
    var xk = x0
    while (math.abs(math.pow(xk, 3) - c) < epsilon) {
      xk = cubicRootStep(c, xk)
    }
    xk
  }

  def cubicRoot(c: Double): Double = {
    require (c >= 0)
    if (c == 0) 0 else cubicRootErr(c, 1.0, 0.0001)
  }

  sealed abstract class BSTree
  case object Empty extends BSTree
  case class Node(left: BSTree, data: Int, right: BSTree) extends BSTree

  def repOk(t: BSTree): Boolean = {
    def check(t: BSTree, min: Int, max: Int): Boolean = t match {
      case Empty => true
      case Node(left, data, right) => data >= min && data < max && check(left, min, data) && check(right, data, max)
    }
    check(t, Int.MinValue, Int.MaxValue)
  }

  def insert(t: BSTree, n: Int): BSTree = t match {
    case Empty => Node(Empty, n, Empty)
    case Node(left, data, right) => {
      if (n < data) Node(insert(left, n), data, right)
      else Node(left, data, insert(right, n))
    }
  }

  def deleteMin(t: BSTree): (BSTree, Int) = {
    require(t != Empty)
    (t: @unchecked) match {
      case Node(Empty, data, right) => (right, data)
      case Node(left, data, right) => {
        val (newLeft, deleted) = deleteMin(left)
        (Node(newLeft, data, right), deleted)
      }
    }
  }

  def delete(t: BSTree, n: Int): (BSTree, Boolean) = t match {
    case Empty => (Empty, false)
    case node @ Node(left, data, Empty) => {
      if (n == data) (left, true)
      else if (n < data) {
        val (newLeft, deleted) = delete(left, n)
        (Node(newLeft, data, Empty), deleted)
      } else (node, false)
    }
    case Node(left, data, right) => {
      if (n == data) {
        val (newRight, deleted) = deleteMin(right)
        (Node(left, deleted, newRight), true)
      } else if (n < data) {
        val (newLeft, deleted) = delete(left, n)
        (Node(newLeft, data, right), deleted)
      } else {
        val (newRight, deleted) = delete(right, n)
        (Node(left, data, newRight), deleted)
      }
    }
  }

}
