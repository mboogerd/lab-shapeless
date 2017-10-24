package com.github.mboogerd.json

/**
 *
 */
trait JsonCollectionInstances {

  implicit def listEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[List[A]] =
    JsonEncoder.pure(list => JsonArray(list.map(enc.encode)))

  implicit def optionEncoder[A](implicit enc: JsonEncoder[A]): JsonEncoder[Option[A]] =
    JsonEncoder.pure(opt => opt.map(enc.encode).getOrElse(JsonNull))

}
