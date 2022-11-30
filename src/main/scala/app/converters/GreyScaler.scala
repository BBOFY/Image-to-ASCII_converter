package app.converters

import app.models.image.{ImageGrey, ImageRegular}
import app.models.pixel.PixelGrey

object GreyScaler extends Converter[ImageRegular[_<:PixelGrey], ImageGrey] {
	override def convert(image: ImageRegular[_<:PixelGrey]): ImageGrey = {

		var newGrid = Vector.fill(image.height, image.width)(PixelGrey(0))

		for (x <- 0 until image.width) {
			for (y <- 0 until image.height) {
				val greyVal = image.getPixel(x, y).getGreyscale
				newGrid = newGrid.updated(y, newGrid.apply(y).updated(x, PixelGrey(greyVal)))
			}
		}

		new ImageGrey(newGrid)
	}
}
