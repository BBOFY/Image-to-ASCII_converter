package app.handlers.exportHandlers

import app.Commands
import app.builders.ExporterBuilder
import app.inputParser.InputParser
import exporter.text.StdOutputExporter
import handler.Handler

class StdOutputHandler(val exporterBuilder: ExporterBuilder,
					   val parser: InputParser[String]
					  )
  extends ExportHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.nonEmpty && args.head == Commands.outputConsole) {
			val exporter = new StdOutputExporter
			exporterBuilder.registerProperty(exporter)
			parser.removeElements(1)
			None
		}
		else super.handle(args)
	}
}
