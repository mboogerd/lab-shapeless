package com.github.mboogerd

/**
 *
 */
package object csv extends CellCodecInstances with RowCodecInstances {

  def writeCsv[A](values: List[A])(implicit enc: RowEncoder[A]): String =
    values.map(value => enc.encode(value).mkString(",")).mkString("\n")

}