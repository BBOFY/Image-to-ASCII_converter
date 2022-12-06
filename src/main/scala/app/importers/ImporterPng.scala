package app.importers

import app.models.image.{Image, ImageRgb}
import app.models.pixel.PixelRgb

import java.io.File
import javax.imageio.ImageIO

class ImporterPng extends ImporterImageIo {

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
}
