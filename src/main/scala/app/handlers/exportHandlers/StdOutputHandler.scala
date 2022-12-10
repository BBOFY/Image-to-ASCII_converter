package app.handlers.exportHandlers

import app.builders.ExporterBuilder
import app.enums.Commands
import app.inputParser.InputParser
import exporter.text.StdOutputExporter
import handler.Handler

class StdOutputHandler(val exporterBuilder: ExporterBuilder,
					   val parser: InputParser[String]
					  )
  extends ExportHandler {

	/**
	 * Checks, if args contain command for console output
	 * @param args Arguments to choose from to handle
	 * @return The next handler or none if argument found
	 */
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.outputConsole.toString)
			return super.handle(args)

		val exporter = new StdOutputExporter
		exporterBuilder.registerProperty(exporter)
		parser.removeElements(1)
		None

	}
}
