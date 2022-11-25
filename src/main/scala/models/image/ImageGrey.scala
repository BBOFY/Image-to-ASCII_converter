package models.image

import models.pixel.PixelGrey

class ImageGrey(width: Int, height: Int) extends Image[PixelGrey](width, height) {

	override protected var _grid: Vector[Vector[PixelGrey]] = {
		Vector.fill(_height, _width) {
			PixelGrey(0)
		}
	}
}
