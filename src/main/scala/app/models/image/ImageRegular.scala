package app.models.image

import app.models.pixel.PixelNumeric

abstract class ImageRegular[T <: PixelNumeric[_]](private val grid: Vector[Vector[T]]) extends Image[T](grid) {

}
