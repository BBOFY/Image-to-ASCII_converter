package app.handlers.importHandlers

import app.handlers.Handler
import app.importers.ImportRandom
import app.inputParser.InputParser
import app.inputParser.commands.Commands
import app.processor.ImageProcessor

class ImportRandomHandler(val importer: ImportRandom, val imgProcessor: ImageProcessor, val parser: InputParser[String], val cmds: Commands) extends ImportHandler {
	// parasite function ?
	override protected def validPostfixes: Seq[String] = Seq.empty

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == cmds.cmdImageRandom) {
			imgProcessor.loadImage(importer.doImport())
			parser.removeElements(1)
			None
		}
		else nextHandler
	}
}
