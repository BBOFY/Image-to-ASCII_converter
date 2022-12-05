package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class RotateFilter(val rotation: Int) extends ImageFilter {

	override def apply(image: ImageGrey): ImageGrey = {

		rotation % 360 match {
			case 0 => image
			case 90 | -270 =>
				rotateClockVise90(image)

			case 180 | -180 =>
				rotateClockVise90(rotateClockVise90(image))

			case 270 | -90 =>
				rotateAntiClockVise90(image)
		}
	}

	protected def rotateClockVise90(image: ImageGrey): ImageGrey = {
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty
		for (col <- 0 until image.width) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (row <- image.getGrid) {
				newRow = newRow.appended(row.apply(col))
			}
			newGrid = newGrid.prepended(newRow)
		}
		new ImageGrey(newGrid)
	}

	protected def rotateAntiClockVise90(image: ImageGrey): ImageGrey = {
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty
		for (col <- 0 until image.width) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (row <- image.getGrid) {
				newRow = newRow.prepended(row.apply(col))
			}
			newGrid = newGrid.appended(newRow)
		}
		new ImageGrey(newGrid)
	}
}
