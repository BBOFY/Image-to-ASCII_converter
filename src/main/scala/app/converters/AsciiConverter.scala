package app.converters

import app.models.conversionTables.{BourkeTable, ConversionTable}
import app.models.image.{Image, ImageAscii, ImageGrey}
import app.models.pixel.{Pixel, PixelAscii}


class AsciiConverter(protected val table: ConversionTable[Int, Char] = new BourkeTable)
  extends Converter[ImageGrey, ImageAscii] {
	
	override def convert(image: ImageGrey): ImageAscii = {

		var newGrid: Vector[Vector[PixelAscii]] = Vector.empty

		for (rowIdx <- 0 until image.height) {
			var newRow: Vector[PixelAscii] = Vector.empty
			for (columnIdx <- 0 until image.width) {
				val pixelValue = image.getPixel(columnIdx, rowIdx).value_
				newRow = newRow.appended(PixelAscii(
					table.getTableValue(pixelValue)
				))
			}
			newGrid = newGrid.appended(newRow)
		}

		new ImageAscii(newGrid)
	}
}
