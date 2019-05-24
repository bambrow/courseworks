package hw02

import org.scalactic.Tolerance._
import org.scalatest.FlatSpec
import scala.language.postfixOps
import hw02._

class hw02Spec extends FlatSpec {

  val s1 = Set(1)
  val s4 = Set(2)
  val s7 = Set.empty
  val s8 = Set((x: Int) => x % 2 == 0)

  "set" should "correctly behaves in constructor, contains and insert" in {
    val s2 = s1.insert(2)
    val s3 = s2.insert(3)
    assert(s1.contains(1) === true)
    assert(s2.contains(1) === true)
    assert(s1.contains(2) === false)
    assert(s2.contains(2) === true)
    assert(s3.contains(3) === true)
    assert(s4.contains(1) === false)
    assert(s7.contains(1) === false)
    assert(s8.contains(0) === true)
    assert(s8.contains(1) === false)
  }

  "set" should "correctly behaves in filter" in {
    val s9 = s8.filter(_ < 5)
    assert(s9.contains(2) === true)
    assert(s9.contains(6) === false)
    val s10 = s9.filter(_ > 3)
    assert(s10.contains(4) === true)
    assert(s10.contains(2) === false)
  }

  "set" should "correctly behaves in union and intersect" in {
    val s3 = s1.insert(2).insert(3)
    val s6 = s4.insert(3).insert(4)
    val s11 = s3.union(s6)
    val s12 = s3.intersect(s6)
    assert(s11.contains(1) === true)
    assert(s11.contains(2) === true)
    assert(s11.contains(3) === true)
    assert(s11.contains(4) === true)
    assert(s12.contains(1) === false)
    assert(s12.contains(2) === true)
    assert(s12.contains(3) === true)
    assert(s12.contains(4) === false)
    val s13 = s8.union(Set(_ % 2 == 1))
    assert(s13.contains(1) === true)
    assert(s13.contains(2) === true)
    val s14 = s8.intersect(Set(_ % 2 == 1))
    assert(s14.contains(1) === false)
    assert(s14.contains(2) === false)
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

  def treeFromList(l: List[Int]): BSTree = ((Empty: BSTree) /: l)(_.insert(_))

  "BSTree" should "do insertion correctly" in {
    assert(Empty.insert(2) === t1)
    assert(t6.insert(2) === t2)
    assert(t1.insert(4) === t7)
    assert(Empty.insert(2).insert(4) === t7)
    assert(Empty.insert(2).insert(1) === t10)
    assert(Empty.insert(2).insert(1).insert(3) === t8)
  }

  "BSTree" should "sum all elements correctly" in {
    assert(Empty.sum === 0)
    assert(t1.sum === 2)
    assert(t7.sum === 6)
    assert(t8.sum === 6)
    assert(t10.sum === 3)
    assert(treeFromList(List(5, 3, 7, 2, 4, 6)).sum === 27)
    assert(treeFromList(List(2, 1, 5, 6, 4, 3)).sum === 21)
  }

  "BSTree" should "behave correctly in strictlySorted" in {
    assert(Empty.strictlySorted === true)
    assert(t1.strictlySorted === true)
    assert(t7.strictlySorted === true)
    assert(t8.strictlySorted === true)
    assert(t10.strictlySorted === true)
    assert(treeFromList(List(5, 3, 7, 2, 4, 6)).strictlySorted === true)
    assert(treeFromList(List(2, 1, 5, 6, 4, 3)).strictlySorted === true)
    assert(t3.strictlySorted === false)
    assert(t4.strictlySorted === false)
    assert(treeFromList(List(4, 4)).strictlySorted === false)
    assert(Node(t8, 4, Empty).strictlySorted === true)
    assert(Node(t8, 2, Empty).strictlySorted === false)
  }

  "BSTree" should "construct list in increasing order correctly" in {
    assert(Empty.toList === List[Int]())
    assert(t1.toList === List(2))
    assert(t7.toList === List(2, 4))
    assert(t8.toList === List(1, 2, 3))
    assert(treeFromList(List(5, 3, 7, 2, 4, 6)).toList === List(2, 3, 4, 5, 6, 7))
    assert(treeFromList(List(2, 1, 5, 6, 4, 3)).toList === List(1, 2, 3, 4, 5, 6))
  }

  "unlines" should "corretly add new line characters and flatten the list" in {
    assert(unlines(List(List())) === List())
    assert(unlines(List(List('a'))) === List('a'))
    assert(unlines(List(List('a'), List('b'))) === List('a', '\n', 'b'))
    assert(unlines(List(List('a', 'a'), List('b', 'b'))) === List('a', 'a', '\n', 'b', 'b'))
    assert(unlines(List(List('a'), List('b'), List('c'))) === List('a', '\n', 'b', '\n', 'c'))
  }

  "firstDay" should "correctly returns the first day of given month and year" in {
    assert(firstDay(1, 2018) === 1)
    assert(firstDay(2, 2018) === 4)
    assert(firstDay(3, 2018) === 4)
    assert(firstDay(4, 2018) === 0)
    assert(firstDay(5, 2018) === 2)
    assert(firstDay(6, 2018) === 5)
  }

  "pixel" should "correctly construct a pixel picture" in {
    assert(pixel('a').width === 1)
    assert(pixel('a').height === 1)
    assert(pixel('a').pxx.head.head === 'a')
  }

  "above" should "correctly place picture above" in {
    val p1 = pixel('a')
    val p2 = pixel('b')
    assert((p1 above p2).width === 1)
    assert((p1 above p2).height === 2)
    assert((p1 above p2).pxx.length === 2)
    assert((p1 above p2).pxx.head.head === 'a')
    assert((p1 above p2).pxx.last.head === 'b')
  }

  "beside" should "correctly place picture beside" in {
    val p1 = pixel('a')
    val p2 = pixel('b')
    assert((p1 beside p2).width === 2)
    assert((p1 beside p2).height === 1)
    assert((p1 beside p2).pxx.length === 1)
    assert((p1 beside p2).pxx.head.head === 'a')
    assert((p1 beside p2).pxx.head.last === 'b')
  }

  "stack, spread and tile" should "correctly stack, spread and tile pictures" in {
    val p1 = pixel('a')
    val p2 = pixel('b')
    val p3 = pixel('c')
    val p4 = stack(List(p1, p2, p3))
    val p5 = spread(List(p1, p2, p3))
    val p6 = tile(List(List(p1, p2, p3), List(p1, p2, p3), List(p1, p2, p3)))
    assert(p4.width === 1)
    assert(p4.height === 3)
    assert(p4.pxx.length === 3)
    assert(p4.pxx.head.head === 'a')
    assert(p4.pxx.last.head === 'c')
    assert(p5.width === 3)
    assert(p5.height === 1)
    assert(p5.pxx.length === 1)
    assert(p5.pxx.head.head === 'a')
    assert(p5.pxx.head.last === 'c')
    assert(p6.width === 3)
    assert(p6.height === 3)
    assert(p6.pxx.length === 3)
    assert(p6.pxx.head.head === 'a')
    assert(p6.pxx.head.last === 'c')
    assert(p6.pxx.last.head === 'a')
    assert(p6.pxx.last.last === 'c')
  }

  "rightJustify" should "correctly align all the chars and return a picture" in {
    val p1 = rightJustify(3)("a".toList)
    val p2 = rightJustify(3)("ab".toList)
    val p3 = rightJustify(3)("abc".toList)
    assert(p1.pxx.head(0) === ' ')
    assert(p1.pxx.head(1) === ' ')
    assert(p1.pxx.head(2) === 'a')
    assert(p2.pxx.head(0) === ' ')
    assert(p2.pxx.head(1) === 'a')
    assert(p3.pxx.head(0) === 'a')
  }

  "group" should "correctly group the elements according to the offset" in {
    val l1 = List(1, 2, 3, 4, 5, 6, 7, 8)
    val l2 = List('a', 'b', 'c', 'd', 'e', 'f')
    assert(group(1, l1).length === 8)
    assert(group(2, l1).length === 4)
    assert(group(3, l1).length === 3)
    assert(group(4, l1).length === 2)
    assert(group(1, l1).head === List(1))
    assert(group(2, l1).head === List(1, 2))
    assert(group(3, l1).head === List(1, 2, 3))
    assert(group(4, l1).head === List(1, 2, 3, 4))
    assert(group(3, l1).last === List(7, 8))
    assert(group(5, l2).last === List('f'))
  }

  "dayPics" should "correctly construct a list of 42 pictures as indicated" in {
    val front = List[Picture]() padTo(4, pixel(' '))
    val end = List[Picture]() padTo(10, pixel(' '))
    val body = ((1 to 28) toList).map(x => x.toString).map(x => x.toList).map(x => if (x.length == 1) pixel(x.head) else pixel(x.head) beside pixel(x.last))
    assert(dayPics(4, 28) === (front ::: body ::: end))
  }


  val cal_2018_02 =
    """ Su Mo Tu We Th Fr Sa
      |              1  2  3
      |  4  5  6  7  8  9 10
      | 11 12 13 14 15 16 17
      | 18 19 20 21 22 23 24
      | 25 26 27 28         """.stripMargin

  "calendar" should "print the calendar of the given month" in {
    assert(calendar(2018, 2).toString === cal_2018_02)
  }


  val cal_2018_01 =
    """ Su Mo Tu We Th Fr Sa
      |     1  2  3  4  5  6
      |  7  8  9 10 11 12 13
      | 14 15 16 17 18 19 20
      | 21 22 23 24 25 26 27
      | 28 29 30 31         """.stripMargin

  val cal_2018_03 =
    """ Su Mo Tu We Th Fr Sa
      |              1  2  3
      |  4  5  6  7  8  9 10
      | 11 12 13 14 15 16 17
      | 18 19 20 21 22 23 24
      | 25 26 27 28 29 30 31""".stripMargin

  val cal_2018_04 =
    """ Su Mo Tu We Th Fr Sa
      |  1  2  3  4  5  6  7
      |  8  9 10 11 12 13 14
      | 15 16 17 18 19 20 21
      | 22 23 24 25 26 27 28
      | 29 30               """.stripMargin

  val cal_2018_05 =
    """ Su Mo Tu We Th Fr Sa
      |        1  2  3  4  5
      |  6  7  8  9 10 11 12
      | 13 14 15 16 17 18 19
      | 20 21 22 23 24 25 26
      | 27 28 29 30 31      """.stripMargin

  val cal_2018_06 =
    """ Su Mo Tu We Th Fr Sa
      |                 1  2
      |  3  4  5  6  7  8  9
      | 10 11 12 13 14 15 16
      | 17 18 19 20 21 22 23
      | 24 25 26 27 28 29 30""".stripMargin

  "calendar" should "print the calendar of the given months" in {
    assert(calendar(2018, 1).toString === cal_2018_01)
    assert(calendar(2018, 3).toString === cal_2018_03)
    assert(calendar(2018, 4).toString === cal_2018_04)
    assert(calendar(2018, 5).toString === cal_2018_05)
    assert(calendar(2018, 6).toString === cal_2018_06)
  }

}
