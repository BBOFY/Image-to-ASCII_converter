package app.importers

import app.models.image.ImageRgb
import app.models.pixel.PixelRgb

import java.io.File
import javax.imageio.{IIOException, ImageIO}


abstract class ImporterImageIo extends ImageImporter {

	@throws[IIOException]
	override def doImport(): ImageRgb = {
		val img = ImageIO.read(new File(_path))

		var imgGrid: Vector[Vector[PixelRgb]] = Vector.empty

		for (y <- 0 until img.getHeight) {
			imgGrid = imgGrid.appended(Vector.empty)
			for (x <- 0 until img.getWidth) {
				val pixel = processRgbValue(img.getRGB(x, y))
				imgGrid = imgGrid.updated(y, imgGrid.apply(y).appended(pixel))
			}
		}

		new ImageRgb(imgGrid)
	}

	protected var _path: String = ""

	def setPath(path: String): Unit = _path = path

	protected def processRgbValue(rgb: Int): PixelRgb = {
		val r: Int = (rgb & 0x00_ff_00_00) >>> 16
		val g: Int = (rgb & 0x00_00_ff_00) >>> 8
		val b: Int = rgb & 0x00_00_00_ff
		PixelRgb(r, g, b)
	}

}
