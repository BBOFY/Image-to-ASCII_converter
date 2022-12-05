package app.converters

import app.models.image.{Image, ImageAscii, ImageGrey}
import app.models.pixel.{Pixel, PixelAscii}


class AsciiLinearConverter(protected val conversionTable: String = "@B%8&WM#*ahkbdpqwmZ0QLCJUYzcvunxrjft/\\|()1{}[]?-_+~<>i!;:,\"^`'. ")
  extends Converter[ImageGrey, ImageAscii] {
	
	override def convert(image: ImageGrey): ImageAscii = {

		var newGrid: Vector[Vector[PixelAscii]] = Vector.empty

		for (rowIdx <- 0 until image.height) {
			var newRow: Vector[PixelAscii] = Vector.empty
			for (columnIdx <- 0 until image.width) {
				newRow = newRow.appended(PixelAscii(
					findAsciiValue(image.getPixel(columnIdx, rowIdx).value_))
				)
			}
			newGrid = newGrid.appended(newRow)
		}

		new ImageAscii(newGrid)
	}

	protected def findAsciiValue(numValue: Int): Char = {
		val resultIdx = (numValue / 4.0).floor.toInt
		conversionTable.charAt(resultIdx)
	}
}
