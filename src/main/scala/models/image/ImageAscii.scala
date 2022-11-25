package models.image

import models.pixel.PixelAscii

class ImageAscii(width: Int, height: Int) extends Image[PixelAscii](width, height) {
	override protected var _grid: Vector[Vector[PixelAscii]] = {
		Vector.fill(_height, _width) {
			PixelAscii(0.toChar)
		}
	}
}
