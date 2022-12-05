package app.handlers.importHandlers

import app.handlers.Handler
import app.importers.ImporterPng
import app.processor.ImageProcessorImpl

class ImportPngHandler (val imgProcessor: ImageProcessorImpl) extends ImportHandler {
	override protected def validCommandForms: Seq[String] = Seq(
		".png", ".PNG"
	)

	override def handle(path: String): Option[Handler[String]] = {
		if (validCommandForms.exists(postfix => path.endsWith(postfix))) {
			val image = new ImporterPng(path)
			imgProcessor.loadImage(image.doImport())
			println("HERE")
			None
		}
		else nextHandler
	}
}
