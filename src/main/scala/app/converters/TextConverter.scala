package app.converters

import app.models.image.ImageAscii

class TextConverter extends Converter[ImageAscii, String] {

	// TODO: Fix conversion - parasitic new lines
	override def convert(image: ImageAscii): String = {
		val grid = image.getGrid
		var result: String = ""

		for (row <- grid) {
			for (pixel <- row) {
				result = result.concat(pixel.value.toString)
			}
//			result = result.concat("\n")
		}
		result
	}
}
