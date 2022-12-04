package app.converters

import app.models.image.{ImageAscii, ImageGrey}
import app.models.pixel.PixelAscii

class AsciiUserDefinedConverter(val customTable: String) extends AsciiConverter {


	override protected def findAsciiValue(numValue: Int): Char = {
		val resultIdx = ((customTable.length - 1) * numValue / 255).floor.toInt
		customTable.charAt(resultIdx)
	}
}
