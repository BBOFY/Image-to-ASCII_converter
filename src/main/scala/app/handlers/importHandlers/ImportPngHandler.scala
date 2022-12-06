package app.handlers.importHandlers

import app.handlers.Handler
import app.importers.{ImporterImageIo, ImporterPng}
import app.processor.ImageProcessorImpl

class ImportPngHandler (val importer: ImporterImageIo, val imgProcessor: ImageProcessorImpl) extends ImportHandler {
	override protected def validCommandForms: Seq[String] = Seq(
		".png", ".PNG"
	)

	override def handle(path: String): Option[Handler[String]] = {
		if (validCommandForms.exists(postfix => path.endsWith(postfix))) {
			importer.setPath(path)
			imgProcessor.loadImage(importer.doImport())
			None
		}
		else nextHandler
	}
}
