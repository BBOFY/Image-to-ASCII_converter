package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey

class RotateFilter(val rotation: Int) extends ImageFilter {

	override def apply(image: ImageGrey): ImageGrey = {

		rotation % 360 match {
			case 0 => image
			case 90 | -270 => {}
			case 180 | -180 => {}
			case 270 | -90 => {}
		}
		image
	}
}
