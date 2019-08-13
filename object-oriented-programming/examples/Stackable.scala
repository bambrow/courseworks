package exam

object Stackable {

  trait Term {
    def value: Int = 0
  }

  class Sum(x: Int, y: Int) extends Term {
    override def value: Int = x + y
  }

  trait Double extends Term {
    override def value: Int = super.value * 2
  }

  trait Decrement extends Term {
    override def value: Int = super.value - 1
  }

  trait Nullify extends Term {
    override def value: Int = 0
  }

  def main(args: Array[String]): Unit = {
    println(new Sum(1, 2) with Double with Decrement value)
    println(new Sum(1, 2) with Decrement with Double value)
    println(new Sum(1, 2) with Nullify with Decrement value)
  }

}
