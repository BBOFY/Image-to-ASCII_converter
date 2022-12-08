package app.importers
import app.models.image.{Image, ImageRgb}
import importer._

trait ImageImporter extends Importer[ImageRgb] {
	override def doImport(): ImageRgb
}
