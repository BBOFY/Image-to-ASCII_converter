package app.filters.specific

import app.filters.VariableFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey


class RotateFilter extends VariableFilter {

	private var _rotation: Int = 0

	/**
	 * 	Rotates the image by previously defined value divisible by 90 in degrees
	 *  @param image Image to rotate
	 *  @return Rotated image
	 */
	override def apply(image: ImageGrey): ImageGrey = {

		_rotation % 360 match {
			case 0 => image
			case 90 =>
				rotateClockVise90(image)

			case 180 =>
				rotateClockVise90(rotateClockVise90(image))

			case 270 =>
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

	override def setValue(rotation: Int): Unit = {
		if (rotation % 90 != 0)
			throw new IllegalArgumentException("Rotation amount must be divisible by 90")
		_rotation = (rotation % 360 + 360) % 360
	}

	override def equals(obj: Any): Boolean = {
		obj match {
			case that: RotateFilter => that._rotation == this._rotation
			case _ => false
		}
	}
}
