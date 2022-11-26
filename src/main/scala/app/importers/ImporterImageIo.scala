package app.importers

import java.io.File
import javax.imageio.ImageIO
import java.awt.image.BufferedImage
import app.models.image.{ImageRegular, ImageRgb}
import app.models.pixel.{PixelNumeric, PixelRgb}


class ImporterImageIo(protected val path: String) extends FileInputImporter[ImageRegular[_]](path) {

	override def doImport(): ImageRgb = {

		val importedImage = ImageIO.read(new File(path))

		val newImage = new ImageRgb( importedImage.getWidth, importedImage.getHeight )

		for ( x <- 0 until newImage.width ) {
			for ( y <- 0 until newImage.height ) {
				newImage.setPixel(x, y, processRgbValue(importedImage.getRGB(x, y)))
			}
		}
		newImage
	}

	private def processRgbValue(rgb: Int): PixelRgb = {
		val r: Int = (rgb & 0x00_ff_00_00) >>> 16
		val g: Int = (rgb & 0x00_00_ff_00) >>> 8
		val b: Int = rgb & 0x00_00_00_ff
		PixelRgb(r, g, b)
	}

}
