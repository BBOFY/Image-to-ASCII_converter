package app.converters

import app.models.image.{ImageGrey, ImageRgb}
import app.models.pixel.{PixelGrey, PixelRgb}
import converter.Converter

class GreyScaler extends Converter[ImageRgb, ImageGrey] {

	/**
	 * Converts Rgb image into grey scale image
	 *  @param image Rgb image to convert
	 *  @return Converted image in grey scale
	 */
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

	/**
	 * Converts rgb pixel to grey scale pixel
	 * @param pixelRgb Rgb pixel to convert
	 * @return Grey scale pixel
	 */
	private def getGreyScale(pixelRgb: PixelRgb): PixelGrey = {
		val greyValue = ((0.3 * pixelRgb.value.r) + (0.59 * pixelRgb.value.g) + (0.11 * pixelRgb.value.b)).toInt
		PixelGrey(greyValue)
	}
}
