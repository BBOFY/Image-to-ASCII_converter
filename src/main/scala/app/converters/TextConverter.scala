package app.converters

import app.models.image.ImageAscii

class TextConverter extends Converter[ImageAscii, String] {
	override def convert(item: ImageAscii): String = ???
}
