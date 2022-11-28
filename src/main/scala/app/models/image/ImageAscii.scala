package app.models.image

import app.models.pixel.PixelAscii

class ImageAscii(private val grid: Vector[Vector[PixelAscii]]) extends Image[PixelAscii](grid) {
}
