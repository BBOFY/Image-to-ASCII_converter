package app.importers

import app.models.image.{ImageRegular, ImageRgb}
import app.models.pixel.{PixelNumeric, PixelRgb}


abstract class ImporterImageIo extends FileInputImporter[ImageRegular[_]] {

	protected def processRgbValue(rgb: Int): PixelRgb = {
		val r: Int = (rgb & 0x00_ff_00_00) >>> 16
		val g: Int = (rgb & 0x00_00_ff_00) >>> 8
		val b: Int = rgb & 0x00_00_00_ff
		PixelRgb(r, g, b)
	}

}
