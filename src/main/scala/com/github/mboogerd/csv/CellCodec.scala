package com.github.mboogerd.csv

import scalaz.ValidationNel

/**
 *
 */
object CellCodec {

  // Summoner method
  def apply[A](implicit enc: CellCodec[A]): CellCodec[A] = enc

  // Constructor method
  def pure[A](dec: String ⇒ ValidationNel[CellDecoder.Error, A])(enc: A ⇒ String): CellCodec[A] = new CellCodec[A] {
    override def encode(a: A): String = enc(a)
    override def decode(s: String): ValidationNel[CellDecoder.Error, A] = dec(s)
  }

  // Constructs a codec from a function (with throw protection) and an encoder.
  def safe[A](dec: String ⇒ A)(enc: A ⇒ String): CellCodec[A] = pure(CellDecoder.safeParser(dec))(enc)

  // Combines an existing decoder and encoder into a codec
  def combine[A](dec: CellDecoder[A])(enc: CellEncoder[A]): CellCodec[A] = pure(dec.decode)(enc.encode)
}
trait CellCodec[A] extends CellEncoder[A] with CellDecoder[A]
