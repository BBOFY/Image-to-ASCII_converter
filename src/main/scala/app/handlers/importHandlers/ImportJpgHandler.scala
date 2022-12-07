package app.handlers.importHandlers

import app.handlers.{Handler, SimpleHandler}
import app.importers.{ImporterImageIo, ImporterJpg}
import app.inputParser.commands.Commands
import app.processor.{ImageProcessor, ImageProcessorImpl}

class ImportJpgHandler(val importer: ImporterImageIo, val imgProcessor: ImageProcessor, val cmds: Commands) extends ImportHandler {
	override def validPostfixes: Seq[String] = Seq(
		".jpg", ".jpeg", ".jpe", ".jif", ".jfif", ".jfi",
		".JPG", ".JPEG", ".JPE", ".JIF", ".JFIF", ".JFI"
	)

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if ( args.head == cmds.cmdImage
		  && validPostfixes.exists(postfix => args.apply(1).endsWith(postfix))) {
			importer.setPath(args.apply(1))
			imgProcessor.loadImage(importer.doImport())
			None
		}
		else nextHandler
	}
}