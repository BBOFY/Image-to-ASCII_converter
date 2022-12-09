package app.filters.specific

import app.filters.{ImageFilter, VariableFilter}
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey


class BrightnessFilter extends VariableFilter {

	private var _increment: Int = 0

	override def apply(image: ImageGrey): ImageGrey = {

		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		for (row <- image.getGrid) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (pixel <- row) {
				val brightenedPixel = brightenPixel(_increment, pixel)
				newRow = newRow.appended(brightenedPixel)
			}
			newGrid = newGrid.appended(newRow)
		}

		new ImageGrey(newGrid)

	}

	protected def brightenPixel(brightnessInc: Int, pixel: PixelGrey): PixelGrey = {
		if (brightnessInc < -255) return PixelGrey(0)
		if (brightnessInc > 255) return PixelGrey(255)

		val newPixelValue = pixel.value + brightnessInc
		newPixelValue match {
			case x if x < 0 => PixelGrey(0)
			case x if x > 255 => PixelGrey(255)
			case _ => PixelGrey(newPixelValue)
		}
	}

	override def setValue(increment: Int): Unit = _increment = increment

	override def equals(obj: Any): Boolean = {
		obj match {
			case that: BrightnessFilter => that._increment == this._increment
			case _ => false
		}
	}
}

