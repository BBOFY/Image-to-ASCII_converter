package app.models.image

import app.models.pixel.PixelAscii

class ImageAscii(width: Int, height: Int) extends ImageImpl[PixelAscii](width, height) {
	override protected var _grid: Vector[Vector[PixelAscii]] = {
		Vector.fill(_height, _width) {
			PixelAscii(0.toChar)
		}
	}
}
