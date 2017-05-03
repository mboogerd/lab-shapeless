package com.github.mboogerd.csv

/**
 *
 */
object RowCodec {

  // Summoner method
  def apply[A](implicit enc: RowEncoder[A]): RowEncoder[A] = enc

  // Constructor method
  def pure[A](dec: List[String] ⇒ A, enc: A ⇒ List[String]): RowCodec[A] = new RowCodec[A] {
    override def encode(a: A): List[String] = encode(a)
    override def decode(list: List[String]): A = dec(list)
  }
}
trait RowCodec[A] extends RowEncoder[A] with RowDecoder[A] {

}
