package app.handlers.importHandlers

import app.handlers.Handler
import app.importers.{ImporterImageIo, ImporterPng}
import app.models.commands.Commands
import app.processor.{ImageProcessor, ImageProcessorImpl}

class ImportPngHandler(val importer: ImporterImageIo, val imgProcessor: ImageProcessor, val cmds: Commands) extends ImportHandler {
	override protected def validPostfixes: Seq[String] = Seq(
		".png", ".PNG"
	)

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == cmds.cmdImage
		  && validPostfixes.exists(postfix => args.apply(1).endsWith(postfix))) {
			importer.setPath(args.apply(1))
			imgProcessor.loadImage(importer.doImport())
			None
		}
		else nextHandler
	}
}
