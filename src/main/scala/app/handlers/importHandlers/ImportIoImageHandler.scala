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

	/**
	 * Checks, if there is command for image from file import, together with its one argument.
	 * Correctness of argument is compared with valid file formats defined in child classes.
	 * @param args Arguments to choose from to handle
	 * @return The next handler, or none if argument is found and correctly processed or args are empty
	 */
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.image.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || Commands.isCommand(args.tail.head))
			return None

		if (!validPostfixes.exists(postfix =>
			args.tail.head.endsWith(postfix))
		)
			return super.handle(args)

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
}
