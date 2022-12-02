package app.handlers.importHandlers

import app.handlers.{Handler, ImportHandler, SimpleHandler}
import app.importers.ImporterJpg
import app.processor.ImageProcessorImpl

class ImportJpgHandler(val imgProcessor: ImageProcessorImpl.type) extends ImportHandler {
	override def validCommandForms: Seq[String] = Seq(
		".jpg", ".jpeg", ".jpe", ".jif", ".jfif", ".jfi",
		".JPG", ".JPEG", ".JPE", ".JIF", ".JFIF", ".JFI"
	)

	override def handle(path: String): Option[Handler[String]] = {

		if ( validCommandForms.exists(postfix => path.endsWith(postfix))) {
			val image = new ImporterJpg(path)
			imgProcessor.loadImage(image.doImport())
			None
		}
		else nextHandler
	}
}