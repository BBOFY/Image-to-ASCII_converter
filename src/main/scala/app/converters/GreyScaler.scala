package app.converters

import app.models.image.{Image, ImageGrey, ImageRgb}
import app.models.pixel.{PixelGrey, PixelRgb}

class GreyScaler extends Converter[ImageRgb, ImageGrey] {
	override def convert(image: ImageRgb): ImageGrey = {

		var newGrid = Vector.fill(image.height, image.width)(PixelGrey(0))

		for (x <- 0 until image.width) {
			for (y <- 0 until image.height) {
				val greyPixel = getGreyScale(image.getPixel(x, y))
				newGrid = newGrid.updated(y, newGrid.apply(y).updated(x, greyPixel))
			}
		}

		new ImageGrey(newGrid)
	}

	private def getGreyScale(pixelRgb: PixelRgb): PixelGrey = {
		val greyValue = ((0.3 * pixelRgb.value_.r) + (0.59 * pixelRgb.value_.g) + (0.11 * pixelRgb.value_.b)).toInt
		PixelGrey(greyValue)
	}
}
