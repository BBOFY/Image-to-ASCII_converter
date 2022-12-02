package app.models.image

import app.models.pixel.PixelGrey

class ImageGrey(private val grid: Vector[Vector[PixelGrey]]) extends ImageRegular[PixelGrey](grid) {
	override def getGrid: Vector[Vector[PixelGrey]] = _grid
}
