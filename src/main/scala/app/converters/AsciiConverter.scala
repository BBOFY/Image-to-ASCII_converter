package app.converters

import app.models.conversionTables.{BourkeTable, ConversionTable}
import app.models.image.{Image, ImageAscii, ImageGrey}
import app.models.pixel.{Pixel, PixelAscii}
import converter.Converter


class AsciiConverter(protected val table: ConversionTable[Int, Char] = new BourkeTable)
  extends Converter[ImageGrey, ImageAscii] {

	@throws[IllegalArgumentException]
	override def convert(image: ImageGrey): ImageAscii = {

		val orgGrid = image.getGrid
		var newGrid: Vector[Vector[PixelAscii]] = Vector.empty

		for (row <- orgGrid) {
			var newRow: Vector[PixelAscii] = Vector.empty
			for (pixel <- row) {
				val pixelValue = pixel.value
				newRow = newRow.appended(
					PixelAscii(
						table.getTableValue(pixelValue)
				))
			}
			newGrid = newGrid.appended(newRow)
		}
		new ImageAscii(newGrid)
	}
}
