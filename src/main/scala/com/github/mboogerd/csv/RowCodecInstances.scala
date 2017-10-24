package com.github.mboogerd.csv

import shapeless.{ ::, Generic, HList, HNil, LabelledGeneric, Lazy }

/**
 *
 */
trait RowCodecInstances {

  implicit val hnilEncoder: RowEncoder[HNil] = RowEncoder.pure(hnil => Nil)

  implicit def hlistEncoderCell[H, T <: HList](implicit
    hEncoder: Lazy[CellEncoder[H]],
    tEncoder: RowEncoder[T]): RowEncoder[H :: T] = RowEncoder.pure {
    case h :: t ⇒ hEncoder.value.encode(h) :: tEncoder.encode(t)
  }

  implicit def hlistEncoderRow[B, R, T <: HList](implicit
    gen: Generic.Aux[B, R],
    hRowEncoder: Lazy[RowEncoder[R]],
    tRowEncoder: RowEncoder[T]): RowEncoder[B :: T] = RowEncoder.pure {
    case h :: t ⇒ hRowEncoder.value.encode(gen.to(h)) ::: tRowEncoder.encode(t)
  }

  implicit def genericEncoder[A, R](implicit
    gen: Generic.Aux[A, R],
    enc: Lazy[RowEncoder[R]]): RowEncoder[A] =
    RowEncoder.pure(a => enc.value.encode(gen.to(a)))
}

object RowCodecInstances extends RowCodecInstances