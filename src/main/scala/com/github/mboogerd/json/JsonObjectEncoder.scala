package com.github.mboogerd.json

/**
 *
 */
trait JsonObjectEncoder[A] extends JsonEncoder[A] {
  def encode(value: A): JsonObject
}

object JsonObjectEncoder {
  def pure[A](fn: A => JsonObject): JsonObjectEncoder[A] = new JsonObjectEncoder[A] {
    def encode(value: A): JsonObject = fn(value)
  }
}
