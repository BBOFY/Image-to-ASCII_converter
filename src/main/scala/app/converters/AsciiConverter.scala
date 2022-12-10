package app.converters

import app.models.conversionTables.{BourkeTable, ConversionTable}
import app.models.image.{ImageAscii, ImageGrey}
import app.models.pixel.PixelAscii
import converter.Converter


class AsciiConverter(protected val table: ConversionTable[Int, Char] = new BourkeTable)
  extends Converter[ImageGrey, ImageAscii] {

	/**
	 * Converts grey scale image into ascii image using conversion table.
	 * If not specified during construction of this class, Bourke table is used as default conversion table
	 * @param image Grey scale image to convert
	 * @return Converted grey scale image as ascii image
	 */
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
