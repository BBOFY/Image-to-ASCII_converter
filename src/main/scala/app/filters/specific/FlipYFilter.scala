package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class FlipYFilter extends ImageFilter {

	override def apply(image: ImageGrey): ImageGrey = {
		val grid = image.getGrid
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		for (row <- grid) {
			newGrid = newGrid.appended(row.reverse)
		}

		new ImageGrey(newGrid)

	}

	override def equals(obj: Any): Boolean = {
		obj match {
			case _: FlipYFilter => true
			case _ => false
		}
	}
}
