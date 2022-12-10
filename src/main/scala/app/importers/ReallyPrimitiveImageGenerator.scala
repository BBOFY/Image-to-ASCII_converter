package app.importers
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb

import scala.util.Random

class ReallyPrimitiveImageGenerator extends ImportRandom {
	override def doImport(): ImageRgb = {

		var grid: Vector[Vector[PixelRgb]] = Vector.empty


		for (y <- 0 to Random.between(0, 400)) {
			var row: Vector[Vector[PixelRgb]] = Vector.empty
			for (x <- 0 to Random.between(0, 400)) {
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
