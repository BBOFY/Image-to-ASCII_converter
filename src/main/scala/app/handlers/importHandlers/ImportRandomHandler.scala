package app.handlers.importHandlers

import app.enums.Commands
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

		if (args.isEmpty)
			return None

		if (args.head != Commands.imageRandom.toString)
			return super.handle(args)

		imgProcessor.loadImage(importer.doImport())
		parser.removeElements(1)
		None

	}
}
