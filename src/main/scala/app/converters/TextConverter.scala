package app.converters

import app.models.image.ImageAscii

class TextConverter extends Converter[ImageAscii, String] {

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
