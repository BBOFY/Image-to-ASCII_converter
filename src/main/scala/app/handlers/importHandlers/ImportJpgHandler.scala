package app.handlers.importHandlers

import app.handlers.{Handler, SimpleHandler}
import app.importers.ImporterJpg
import app.processor.ImageProcessorImpl

class ImportJpgHandler(val importer: ImporterJpg, val imgProcessor: ImageProcessorImpl) extends ImportHandler {
	override def validCommandForms: Seq[String] = Seq(
		".jpg", ".jpeg", ".jpe", ".jif", ".jfif", ".jfi",
		".JPG", ".JPEG", ".JPE", ".JIF", ".JFIF", ".JFI"
	)

	override def handle(path: String): Option[Handler[String]] = {

		if ( validCommandForms.exists(postfix => path.endsWith(postfix))) {
			importer.setPath(path)
			imgProcessor.loadImage(importer.doImport())
			None
		}
		else nextHandler
	}
}