package app.converters

import app.models.image.{Image, ImageGrey, ImageRgb}
import app.models.pixel.{PixelGrey, PixelRgb}

class GreyScaler extends Converter[ImageRgb, ImageGrey] {

	@throws[IllegalArgumentException]
	override def convert(image: ImageRgb): ImageGrey = {
		val orgGrid = image.getGrid
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty
		for (row <- orgGrid) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (pixel <- row) {
				val newPixel = getGreyScale(pixel)
				newRow = newRow.appended(newPixel)
			}
			newGrid = newGrid.appended(newRow)
		}
		new ImageGrey(newGrid)
	}

	private def getGreyScale(pixelRgb: PixelRgb): PixelGrey = {
		val greyValue = ((0.3 * pixelRgb.value.r) + (0.59 * pixelRgb.value.g) + (0.11 * pixelRgb.value.b)).toInt
		PixelGrey(greyValue)
	}
}
