package com.github.mboogerd

/**
 *
 */
package object json extends JsonPrimitiveInstances with JsonCollectionInstances {

  def createEncoder[A](func: A => JsonValue): JsonEncoder[A] =
    new JsonEncoder[A] {
      def encode(value: A): JsonValue = func(value)
    }

}
