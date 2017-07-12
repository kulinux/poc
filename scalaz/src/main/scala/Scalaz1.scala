import scalaz._
import std.option._, std.list._
import syntax.bind._

/**
  * Created by pako on 31/5/17.
  */
object Scalaz1 extends App {
  val res = Apply[Option].apply2(Some(1), Some(2))((a, b) => a + b)
  println(res)


  //val res2 = Traverse[List].traverse(List(1, 2, 3))(i => Some(i))
  //println(res2)

  println(List(List(1)).join)

  println(List(true, true).ifM(List(0, 1), List(2, 3)))

  val length : String => Int = _.length

  println(Functor[Option].map(Some("this is a string"))(length))

  println(Functor[List].map(List("this is a string", "dos", "tres"))(length))
}
