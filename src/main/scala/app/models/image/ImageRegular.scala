package app.models.image

import app.models.pixel.PixelNumeric

abstract class ImageRegular[T <: PixelNumeric[_]](private val w: Int, private val h: Int) extends Image[T](w, h) {

}
