package app.handlers.importHandlers

import app.enums.Commands
import app.importers.ImporterImageIo
import app.inputParser.InputParser
import app.processor.ImageProcessor
import handler.Handler

import javax.imageio.IIOException

abstract class ImportIoImageHandler(private val importer: ImporterImageIo,
									private val imgProcessor: ImageProcessor,
									private val parser: InputParser[String])
  extends ImportHandler {

	protected val validPostfixes: Seq[String]

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.nonEmpty
		  && args.tail.nonEmpty
		  && args.head == Commands.image.toString
		  && validPostfixes.exists(postfix => args.tail.head.endsWith(postfix))
		) {
			importer.setPath(args.tail.head)
			try {
				imgProcessor.loadImage(importer.doImport())
			}
			catch {
				case _: IIOException => return None
			}

			parser.removeElements(2)
			None
		}
		else super.handle(args)
	}
}
