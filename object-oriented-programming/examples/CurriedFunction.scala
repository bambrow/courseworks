package exam

object CurriedFunction {

  trait CurriedFunction2[-T1, -T2, +R] extends Function2[T1, T2, R] {
    def curried(v1: T1)(v2: T2): R = apply(v1, v2)
    def apply(v1: T1): T2 => R = curried(v1)
  }

  object Sum extends Function2[Int, Int, Int] {
    def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  object Sum2 extends CurriedFunction2[Int, Int, Int] {
    def apply(v1: Int, v2: Int): Int = v1 + v2
  }

  def main(args: Array[String]): Unit = {
    println(Sum(2, 3))
    println(Sum2(2)(3))
  }

}
