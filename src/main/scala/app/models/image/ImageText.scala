package app.models.image

import app.models.pixel.PixelText

abstract class ImageText[T <: PixelText[_]](private val w: Int, private val h: Int) extends Image[T](w, h) {

}
