package app.models.image

import app.models.pixel.PixelGrey

class ImageGrey(width: Int, height: Int) extends ImageRegular[PixelGrey](width, height) {

	override protected var _grid: Vector[Vector[PixelGrey]] = {
		Vector.fill(_height, _width) {
			PixelGrey(0)
		}
	}
}
