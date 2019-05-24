package hw04

import org.scalactic.Tolerance._
import org.scalatest.FlatSpec
import hw04._

class hw04Spec extends FlatSpec {

  val t001 = TreeMap(1 -> 1)
  val t011 = TreeMap(1 -> 1, 2 -> 2)
  val m011 = Map(1 -> 1, 2 -> 2)
  val t012 = TreeMap(2 -> 2, 1 -> 1)
  val m012 = Map(2 -> 2, 1 -> 1)
  val t013 = TreeMap(1 -> 1, 2 -> 2, 3 -> 3)
  val t014 = TreeMap(0 -> 0, 1 -> 1, 2 -> 2, 3 -> 3)

  val t021 = TreeMap(1 -> "abc", 2 -> "bcd", 3 -> "cde")
  val m021 = Map(1 -> "abc", 2 -> "bcd", 3 -> "cde")
  val t022 = TreeMap(2 -> "bcd", 3 -> "cde", 1 -> "abc")
  val m022 = Map(2 -> "bcd", 3 -> "cde", 1 -> "abc")
  val t023 = TreeMap(1 -> "abc", 2 -> "bcd", 3 -> "cde", 4 -> "def")

  val t031 = TreeMap(1.1 -> t011, 1.2 -> t012, 1.3 -> t013)
  val m031 = Map(1.2 -> t012, 1.3 -> t013, 1.1 -> t011)
  val t041 = TreeMap("abc" -> 111, "bcd" -> 222, "cde" -> 333)
  val m041 = Map("abc" -> 111, "cde" -> 333, "bcd" -> 222)
  val t051 = TreeMap("abc" -> m011, "bcd" -> m012, "cde" -> m021)
  val m051 = Map("bcd" -> m012, "cde" -> m021, "abc" -> m011)
  val t032 = TreeMap(1.1 -> t011, 1.2 -> t012, 1.3 -> t014)
  val t042 = TreeMap("abc" -> 111, "bcd" -> 233, "cde" -> 333)
  val t052 = TreeMap("abc" -> m022, "bcd" -> m012, "cde" -> m021)

  "TreeMap" should "be compared normally" in {
    assert(t011 == m011)
    assert(t012 == m011)
    assert(t011 == m012)
    assert(t012 == m012)
    assert(t021 == m021)
    assert(t022 == m022)
    assert(t031 == m031)
    assert(t041 == m041)
    assert(t051 == m051)
  }

  "TreeMap" should "do insertion correctly" in {
    assert(TreeMap[Int, Int]() + ((1,1)) == t001)
    assert(t001 + ((2,2)) == t011)
    assert(t001 + ((1,1)) == t001)
    assert(TreeMap[Int, Int]() + ((2,2)) + ((1,1)) == t011)
    assert(t011 + ((3,3)) == t013)
    assert(t011 + ((1,1)) == t011)
    assert(TreeMap[Int, Int]() + ((3,3)) + ((2,2)) + ((1,1)) == t013)
    assert(t013 + ((0,0)) == t014)
    assert(t013 + ((1,1)) + ((3,3)) == t013)
    assert(TreeMap[Int, Int]() + ((3,3)) + ((2,2)) + ((1,1)) + ((0,0)) == t014)
    assert(t021 + ((4, "def")) == t023)
    assert(t031 + ((1.3, t014)) == t032)
    assert(t041 + (("bcd", 233)) == t042)
    assert(t051 + (("abc", m022)) == t052)
  }

  "TreeMap" should "do deletion correctly" in {
    assert(t001 - 1 == TreeMap[Int, Int]())
    assert(t001 - 2 == t001)
    assert(t011 - 2 == t001)
    assert(t011 - 3 == t011)
    assert(t013 - 3 == t011)
    assert(t014 - 0 == t013)
    assert(t023 - 4 == t021)
    assert(t031 - 1.3 + ((1.3, t014)) == t032)
    assert(t041 - "bcd" + (("bcd", 233)) == t042)
    assert(t051 - "abc" + (("abc", m022)) == t052)
    assert(t001 - 1 == t014 - 3 - 1 - 0 - 2)
    assert(t021 - 3 - 1 == t023 - 4 - 1 - 3)
    assert(t031 - 1.3 - 1.5 == t032 - 1.3 - 1.5)
    assert(t041 - "bcd" - "kkk" == t042 - "bcd" - "kkk")
    assert(t051 - "abc" - "bcd" == t052 - "bcd" - "abc")
  }

  "TreeMap" should "do contains correctly" in {
    assert(t001.get(1) == Some(1))
    assert(t001.get(2) == None)
    assert(t011.get(2) == Some(2))
    assert(t011.get(3) == None)
    assert(t021.get(2) == Some("bcd"))
    assert(t023.get(4) == Some("def"))
    assert(t031.get(1.1) == Some(t011))
    assert(t031.get(1.5) == None)
    assert(t041.get("bcd") == Some(222))
    assert(t041.get("kkk") == None)
    assert(t051.get("bcd") == Some(m012))
  }

  "TreeMap" should "generate iterator correctly" in {
    assert(t011.iterator.toList.map(t => t._1) == List(1, 2))
    assert(t012.iterator.toList.map(t => t._1) == List(1, 2))
    assert(t014.iterator.toList.map(t => t._1) == List(0, 1, 2, 3))
    assert(t021.iterator.toList.map(t => t._1) == List(1, 2, 3))
    assert(t021.iterator.toList.map(t => t._2) == List("abc", "bcd", "cde"))
    assert(t022.iterator.toList.map(t => t._1) == List(1, 2, 3))
    assert(t022.iterator.toList.map(t => t._2) == List("abc", "bcd", "cde"))
    assert(t031.iterator.toList.map(t => t._1) == List(1.1, 1.2, 1.3))
    assert(t031.iterator.toList.map(t => t._2) == List(t011, t012, t013))
    assert(t041.iterator.toList.map(t => t._1) == List("abc", "bcd", "cde"))
    assert(t041.iterator.toList.map(t => t._2) == List(111, 222, 333))
    assert(t013.foldLeft(0)((z, t) => z + t._1) == 6)
    assert(t014.foldRight(0)((t, z) => t._1 + z) == 6)
    assert(t023.foldLeft("")((z, t) => z + t._2) == "abcbcdcdedef")
    assert(t023.foldRight("")((t, z) => z + t._2) == "defcdebcdabc")
  }

  "wcMonoid" should "satisfies the monoid laws" in {
    import wcMonoid._
    assert(zero == Stub(""))
    assert(combine(zero, zero) == Stub(""))
    assert(combine(Stub("ab"), Stub("cd")) == Stub("abcd"))
    assert(combine(Stub("ab"), Part("c", 1, "d")) == Part("abc", 1, "d"))
    assert(combine(Part("c", 1, "d"), Stub("ab")) == Part("c", 1, "dab"))
    assert(combine(Part("a", 1, "b"), Part("c", 1, "d")) == Part("a", 3, "d"))
    assert(combine(Part("a", 1, ""), Part("", 1, "d")) == Part("a", 2, "d"))
    val c1 = combine(Part("a", 1, "b"), Part("c", 1, "d"))
    val c2 = combine(c1, Part("e", 1, "f"))
    val c3 = combine(Part("c", 1, "d"), Part("e", 1, "f"))
    val c4 = combine(Part("a", 1, "b"), c3)
    assert(c2 == c4)
  }

  "count" should "correctly count the words in a string" in {
    assert(count("") == 0)
    assert(count("      ") == 0)
    assert(count(" \n  \t     ") == 0)
    assert(count(" \n\n\n\n\n\t\t\t\t\t     ") == 0)
    assert(count("Process finished with exit code 0") == 6)
    assert(count("\nProcess\nfinished\nwith\nexit\ncode\n0\n") == 6)
    assert(count("   a      b     c       d     e      f    ") == 6)
    assert(count("We the People of the United States, " +
      "in Order to form a more perfect Union, establish " +
      "Justice, insure domestic Tranquility, provide for " +
      "the common defence, promote the general Welfare, " +
      "and secure the Blessings of Liberty to ourselves " +
      "and our Posterity, do ordain and establish this " +
      "Constitution for the United States of America.") == 52)
    assert(count("Scala combines object-oriented and functional " +
      "programming in one concise, high-level language. Scala's " +
      "static types help avoid bugs in complex applications, and " +
      "its JVM and JavaScript runtimes let you build high-performance " +
      "systems with easy access to huge ecosystems of libraries.") == 39)
  }

}
