package app.importers
import app.models.image.{Image, ImageRgb}
import importer._

trait ImageImporter extends Importer[ImageRgb] {

	/**
	 *  Imports RGB image from somewhere
	 *  @return imported image as ImageRgb
	 */
	override def doImport(): ImageRgb
}
