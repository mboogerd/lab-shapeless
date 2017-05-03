package com.github.mboogerd.csv

/**
 *
 */

object RowEncoder {
  // Summoner method
  def apply[A](implicit enc: RowEncoder[A]): RowEncoder[A] = enc

  // Constructor method
  def pure[A](func: A ⇒ List[String]): RowEncoder[A] = new RowEncoder[A] {
    override def encode(a: A): List[String] = func(a)
  }
}

trait RowEncoder[A] {
  def encode(a: A): List[String]
}