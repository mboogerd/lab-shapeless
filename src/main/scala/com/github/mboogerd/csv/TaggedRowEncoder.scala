package com.github.mboogerd.csv

/**
 *
 */

object TaggedRowEncoder {
  // Summoner method
  def apply[A](implicit enc: TaggedRowEncoder[A]): TaggedRowEncoder[A] = enc

  // Constructor method
  //  def pure[A](func: A ⇒ List[String]): TaggedRowEncoder[A] = new TaggedRowEncoder[A] {
  //    override def encode(a: A): List[String] = func(a)
  //  }
}

trait TaggedRowEncoder[A] extends RowEncoder[A] {
  def columns: List[String]
}