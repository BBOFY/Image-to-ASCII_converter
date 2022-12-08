package app.converters

import app.models.image.ImageAscii

class TextConverter extends Converter[ImageAscii, String] {
	override def convert(image: ImageAscii): String = {
		val grid = image.getGrid
		var result: String = ""

		for (row <- grid) {
			for ( pixel <- row ) {
				result = result + pixel.value
			}
			result = result + '\n'
		}
		result
	}
}
