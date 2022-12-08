package app.importers
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb

class ReallyPrimitiveImageGenerator extends ImportRandom {
	override def doImport(): ImageRgb = {
		val grid = Vector(Vector(PixelRgb(42, 23, 163)))
		new ImageRgb(grid)
	}
}
