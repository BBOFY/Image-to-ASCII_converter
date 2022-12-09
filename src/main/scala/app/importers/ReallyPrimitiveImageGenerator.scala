package app.importers
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb

class ReallyPrimitiveImageGenerator extends ImportRandom {
	override def doImport(): ImageRgb = {
		val grid = Vector(Vector(PixelRgb(42, 6, 9)))
		new ImageRgb(grid)
	}
}
