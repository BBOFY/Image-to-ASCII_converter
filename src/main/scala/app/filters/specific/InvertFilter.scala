package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class InvertFilter extends ImageFilter {
	/**
	 * Inverts value of all pixels in given image
	 * @param image Image to inverts its pixels on
	 * @return Altered image
	 */
	override def apply(image: ImageGrey): ImageGrey = {
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		for(row <- image.getGrid) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (pixel <- row) {
				newRow = newRow.appended(invertPixel(pixel))
			}
			newGrid = newGrid.appended(newRow)
		}

		new ImageGrey(newGrid)
	}

	protected def invertPixel(pix: PixelGrey): PixelGrey = {
		val newVal = 255 - pix.value
		PixelGrey(newVal)
	}

	override def equals(obj: Any): Boolean = {
		obj match {
			case _: InvertFilter => true
			case _ => false
		}
	}
}
