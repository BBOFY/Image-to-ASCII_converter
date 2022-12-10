package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class FlipXFilter extends ImageFilter {

	/**
	 * Flips image by X axis
	 * @param image Image to flip
	 * @return Flipped image
	 */
	override def apply(image: ImageGrey): ImageGrey = {
		val grid = image.getGrid
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		newGrid = grid.reverse

		new ImageGrey(newGrid)

	}

	override def equals(obj: Any): Boolean = {
		obj match {
			case _: FlipXFilter => true
			case _ => false
		}
	}
}
