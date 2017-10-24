package com.github.mboogerd.json

/**
 *
 */
trait JsonPrimitiveInstances {

  implicit val stringEncoder: JsonEncoder[String] =
    JsonEncoder.pure(str => JsonString(str))

  implicit val doubleEncoder: JsonEncoder[Double] =
    JsonEncoder.pure(num => JsonNumber(num))

  implicit val intEncoder: JsonEncoder[Int] =
    JsonEncoder.pure(num => JsonNumber(num))

  implicit val booleanEncoder: JsonEncoder[Boolean] =
    JsonEncoder.pure(bool => JsonBoolean(bool))

}
