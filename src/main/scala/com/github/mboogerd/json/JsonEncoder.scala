package com.github.mboogerd.json

/**
 *
 */
trait JsonEncoder[A] {
  def encode(a: A): JsonValue
}

object JsonEncoder {
  def apply[A](implicit enc: JsonEncoder[A]): JsonEncoder[A] = enc

  def pure[A](func: A => JsonValue): JsonEncoder[A] = new JsonEncoder[A] {
    def encode(value: A): JsonValue = func(value)
  }
}
