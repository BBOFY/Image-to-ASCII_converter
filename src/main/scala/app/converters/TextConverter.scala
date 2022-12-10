package app.converters

import app.models.image.ImageAscii
import converter.Converter

class TextConverter extends Converter[ImageAscii, String] {

	/**
	 * 	Converts ascii image into plaint text
	 * 	@param image Ascii image to convert
	 *  @return Converted image as text
	 */
	override def convert(image: ImageAscii): String = {
		val grid = image.getGrid
		var result: String = ""

		var head = grid.head
		var tail = grid.tail
		while (tail.nonEmpty) {

			for (pixel <- head)
				result = result + pixel.value
			result = result + '\n'
			head = tail.head
			tail = tail.tail
		}
		for (pixel <- head)
			result = result + pixel.value

		result
	}
}
