package app.handlers.importHandlers

import app.Commands
import app.importers.{ImportRandom, ReallyPrimitiveImageGenerator}
import app.inputParser.InputParser
import app.processor.ImageProcessor
import handler.Handler

class ImportRandomHandler(val imgProcessor: ImageProcessor,
						  val parser: InputParser[String],
						  val importer: ImportRandom = new ReallyPrimitiveImageGenerator
						 )
  extends ImportHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.nonEmpty
		  && args.head == Commands.imageRandom
		) {
			imgProcessor.loadImage(importer.doImport())
			parser.removeElements(1)
			None
		}
		else super.handle(args)
	}
}
