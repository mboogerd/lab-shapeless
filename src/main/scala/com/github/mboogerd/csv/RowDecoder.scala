package com.github.mboogerd.csv

/**
 *
 */
object RowDecoder {
  // Summoner method
  def apply[A](implicit dec: RowDecoder[A]): RowDecoder[A] = dec

  // Constructor method
  def pure[A](func: List[String] ⇒ A): RowDecoder[A] = new RowDecoder[A] {
    override def decode(list: List[String]): A = func(list)
  }
}
trait RowDecoder[A] {
  def decode(list: List[String]): A
}