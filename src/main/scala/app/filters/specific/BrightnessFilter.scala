package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey


class BrightnessFilter(private val increment: Int) extends ImageFilter {

	override def apply(image: ImageGrey): ImageGrey = {

		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		for (row <- image.getGrid) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (pixel <- row) {
				val brightenedPixel = brightenPixel(increment, pixel)
				newRow = newRow.appended(brightenedPixel)
			}
			newGrid = newGrid.appended(newRow)
		}

		new ImageGrey(newGrid)

	}

	protected def brightenPixel(brightnessInc: Int, pixel: PixelGrey): PixelGrey = {
		val newPixelValue = pixel.value_ + brightnessInc
		newPixelValue match {
			case x if x <= 0 => PixelGrey(0)
			case x if x >= 0 => PixelGrey(255)
			case _ => PixelGrey(newPixelValue)
		}
	}
}

