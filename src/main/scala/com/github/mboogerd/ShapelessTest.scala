package com.github.mboogerd

//import shapeless._
//import scalaz.syntax.validation._
//import labelled.{ FieldType, field }
//
//import scalaz.ValidationNel
//
//trait Decoder[L <: HList] {
//  def apply(m: Map[String, Any]): ValidationNel[Throwable, L]
//}
//
//trait LowPriorityFromMap {
//  implicit def hconsFromMap1[K <: Symbol, V, T <: HList](implicit
//    witness: Witness.Aux[K],
//    typeable: Typeable[V],
//    fromMapT: Lazy[Decoder[T]]): Decoder[FieldType[K, V] :: T] = new Decoder[FieldType[K, V] :: T] {
//    def apply(m: Map[String, Any]): Option[FieldType[K, V] :: T] = for {
//      v <- m.get(witness.value.name)
//      h <- typeable.cast(v)
//      t <- fromMapT.value(m)
//    } yield field[K](h) :: t
//  }
//}
//
//object Decoder extends LowPriorityFromMap {
//  implicit val hnilFromMap: Decoder[HNil] = new Decoder[HNil] {
//    def apply(m: Map[String, Any]): ValidationNel[Throwable, HNil] = HNil.successNel
//  }
//
//  implicit def hconsFromMap0[K <: Symbol, V, R <: HList, T <: HList](implicit
//    witness: Witness.Aux[K],
//    gen: LabelledGeneric.Aux[V, R],
//    fromMapH: Decoder[R],
//    fromMapT: Decoder[T]): Decoder[FieldType[K, V] :: T] = new Decoder[FieldType[K, V] :: T] {
//    def apply(m: Map[String, Any]): ValidationNel[Throwable, FieldType[K, V] :: T] = {
//
//      for {
//        v <- m.get(witness.value.name)
//        r <- Typeable[Map[String, Any]].cast(v)
//        h <- fromMapH(r)
//        t <- fromMapT(m)
//      } yield field[K](gen.from(h)) :: t
//    }
//  }
//}
//
//class ConvertHelper[A] {
//  def from[R <: HList](m: Map[String, Any])(implicit
//    gen: LabelledGeneric.Aux[A, R],
//    fromMap: Decoder[R]): Option[A] = fromMap(m).map(gen.from(_))
//}
//object ConvertHelper {
//  def to[A]: ConvertHelper[A] = new ConvertHelper[A]
//}
/**
 *
 */
object ShapelessTest extends App {

  case class Address(street: String, zip: Int)
  //  case class Person(name: String, address: Address)
  //
  //  val mp = Map(
  //    "name" -> "Tom",
  //    "address" -> Map("street" -> "Jefferson st", "zip" -> 10000)
  //  )
  //
  //  println(ConvertHelper.to[Person].from(mp))

}
