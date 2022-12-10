package app.handlers.exportHandlers

import app.builders.ExporterBuilder
import app.enums.Commands
import app.inputParser.InputParser
import exporter.text.{FileOutputExporter, TextExporter}
import handler.Handler

class FileOutputHandler(val exporterBuilder: ExporterBuilder,
						val parser: InputParser[String]
					   )
  extends ExportHandler {

	/**
	 * Checks, if args contain command for file output and for path of file to export to as argument
	 * @param args Arguments to choose from to handle
	 * @return The next handler or none if argument found
	 */
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.outputFile.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || Commands.isCommand(args.tail.head))
			return None

		val path = args.tail.head
		val exporter: TextExporter = new FileOutputExporter(path)
		exporterBuilder.registerProperty(exporter)
		parser.removeElements(2)
		None

	}
}
