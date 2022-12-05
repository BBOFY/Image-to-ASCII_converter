package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class InvertFilter extends ImageFilter {
	/**
	 * Applies the specific filter on inserted image
	 *
	 * @param image on which filter will be applied
	 * @return item with applied filter
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
		val newVal = 255 - pix.value_
		PixelGrey(newVal)
	}
}
