package exam

object Overloading {

  class A {

    def m(o: Any): Any = {
      print(1)
      o
    }

    def m(a1: A, a2: A): A = {
      print(3)
      this
    }

  }

  class B extends A {

    override def m(o: Any): Unit = print(5)

    def m(b: B, o: Any): B = {
      print(6)
      b
    }

    def m(i: Int): Unit = print(9)

  }

  def main(args: Array[String]): Unit = {
    val c: Char = 'a'
    val a: A = new B
    val b: B = new B
    a.m(b, a).m(c)
    println()
    b.m(b, a).m(c)
  }

}
