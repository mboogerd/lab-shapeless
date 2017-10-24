package com.github.mboogerd.json

import shapeless.{ ::, HList, HNil, LabelledGeneric, Lazy, Witness }
import shapeless.labelled.FieldType

/**
 *
 */
trait JsonGenerics {

  implicit val hnilEncoder: JsonObjectEncoder[HNil] = JsonObjectEncoder.pure(hnil => JsonObject(Nil))

  implicit def hlistObjectEncoder[K <: Symbol, H, T <: HList](
    implicit
    witness: Witness.Aux[K],
    hEncoder: Lazy[JsonEncoder[H]],
    tEncoder: JsonObjectEncoder[T]
  ): JsonObjectEncoder[FieldType[K, H] :: T] = {
    val fieldName: String = witness.value.name
    JsonObjectEncoder.pure { hList =>
      val head = hEncoder.value.encode(hList.head)
      val tail = tEncoder.encode(hList.tail)
      JsonObject((fieldName, head) :: tail.fields)
    }
  }

  implicit def genericObjectEncoder[A, H <: HList](implicit
    gen: LabelledGeneric.Aux[A, H],
    hEncoder: JsonObjectEncoder[H]): JsonObjectEncoder[A] = {
    JsonObjectEncoder.pure(a ⇒ hEncoder.encode(gen.to(a)))
  }
}
