package app.models.image

import app.models.pixel.PixelRgb

class ImageRgb(width: Int, height: Int) extends ImageImpl[PixelRgb](width, height) {
	override protected var _grid: Vector[Vector[PixelRgb]] = {
		Vector.fill(_height, _width) {
			PixelRgb(0, 0, 0)
		}
	}
}
