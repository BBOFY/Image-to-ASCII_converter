package app.models.image

import app.models.pixel.PixelRgb

class ImageRgb(private val grid: Vector[Vector[PixelRgb]]) extends ImageRegular[PixelRgb](grid) {

}
