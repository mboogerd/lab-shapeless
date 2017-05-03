package com.github.mboogerd.csv

/**
 *
 */

object CellEncoder {
  // Summoner method
  def apply[A](implicit enc: CellEncoder[A]): CellEncoder[A] = enc

  // Constructor method
  def pure[A](func: A ⇒ String): CellEncoder[A] = new CellEncoder[A] {
    override def encode(a: A): String = func(a)
  }
}

trait CellEncoder[A] {
  def encode(a: A): String
}
