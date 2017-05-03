
package com.github.mboogerd.csv

import scalaz.{ Validation, ValidationNel, \/ }
/**
 *
 */
object CellDecoder {

  sealed trait Error

  case class ClassifiedError(code: String, description: String) extends Error

  case class Uncaught(throwable: Throwable) extends Error

  def safeParser[A](parse: (String) ⇒ A): String ⇒ ValidationNel[Error, A] =
    s ⇒ \/.fromTryCatchNonFatal(parse(s)).leftMap(Uncaught.apply).validationNel

  // Summoner method
  def apply[A](implicit dec: CellDecoder[A]): CellDecoder[A] = dec

  // Constructor method
  def pure[A](parse: String ⇒ ValidationNel[CellDecoder.Error, A]): CellDecoder[A] = new CellDecoder[A] {
    override def decode(s: String): ValidationNel[CellDecoder.Error, A] = parse(s)
  }

  // Constructs a decoder mapping non-fatal exceptions to Uncaught
  def safe[A](parse: String ⇒ A): CellDecoder[A] = pure(safeParser(parse))

}

trait CellDecoder[A] {
  def decode(s: String): ValidationNel[CellDecoder.Error, A]
}
