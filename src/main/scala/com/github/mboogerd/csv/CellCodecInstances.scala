package com.github.mboogerd.csv

/**
 *
 */
trait CellCodecInstances {

  implicit val stringCodec: CellCodec[String] = CellCodec.safe(identity)(identity)

  implicit val intCodec: CellCodec[Int] = CellCodec.safe(_.toInt)(_.toString)

  implicit val booleanCodec: CellCodec[Boolean] = CellCodec.safe(_ == "yes")(b ⇒ if (b) "yes" else "no")

}

object CellCodecInstances extends CellCodecInstances