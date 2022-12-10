package app.importers
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb

import scala.util.Random

/**
 * Generates ImageRgb with random width and height and with random individual pixels
 */
class ReallyPrimitiveImageGenerator extends ImportRandom {
	override def doImport(): ImageRgb = {

		var grid: Vector[Vector[PixelRgb]] = Vector.empty

		val width = Random.between(0, 400)
		val height = Random.between(0, 400)

		for (y <- 0 to height) {
			var row: Vector[PixelRgb] = Vector.empty
			for (x <- 0 to width) {
				val r = Random.between(0, 256)
				val g = Random.between(0, 256)
				val b = Random.between(0, 256)
				val pixel = PixelRgb(r, g, b)
				row = row.appended(pixel)
			}
			grid = grid.appended(row)
		}

		new ImageRgb(grid)
	}
}
