package app.handlers.importHandlers

import app.Commands
import app.handlers.Handler
import app.importers.{ImportRandom, PrimitiveImageGenerator}
import app.inputParser.InputParser
import app.processor.ImageProcessor

class ImportRandomHandler(val imgProcessor: ImageProcessor,
						  val parser: InputParser[String],
						  val importer: ImportRandom = new PrimitiveImageGenerator
						 )
  extends ImportHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == Commands.imageRandom) {
			imgProcessor.loadImage(importer.doImport())
			parser.removeElements(1)
			None
		}
		else nextHandler
	}
}
