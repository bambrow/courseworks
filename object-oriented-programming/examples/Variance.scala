package exam

object Variance {

  class CoVar[+T](x: T) {
    def m1: T = x
    def m2[R >: T](y: R): List[R] = List(x, y)
  }

  class ContraVar[-T, R >: T](x: R) {
    def m1: R = x
    def m2(y: T): List[R] = List(x, y)
  }

}
