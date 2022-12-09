package app.handlers.importHandlers

import app.Commands
import app.importers.ImporterImageIo
import app.inputParser.InputParser
import app.processor.ImageProcessor
import handler.Handler

abstract class ImportIoImageHandler(private val importer: ImporterImageIo,
									private val imgProcessor: ImageProcessor,
									private val parser: InputParser[String])
  extends ImportHandler {

	protected val validPostfixes: Seq[String]

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == Commands.image
		  && validPostfixes.exists(postfix => args.apply(1).endsWith(postfix))) {
			importer.setPath(args.apply(1))
			imgProcessor.loadImage(importer.doImport())
			parser.removeElements(2)
			None
		}
		else super.handle(args)
	}
}
