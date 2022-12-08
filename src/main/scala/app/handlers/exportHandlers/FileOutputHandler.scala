package app.handlers.exportHandlers

import app.Commands
import app.builders.ExporterBuilder
import app.inputParser.InputParser
import exporter.text.{FileOutputExporter, TextExporter}
import handler.Handler

class FileOutputHandler(val exporterBuilder: ExporterBuilder,
						val parser: InputParser[String]
					   )
  extends ExporterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.nonEmpty && args.tail.nonEmpty && args.head == Commands.outputFile) {
			val path = args.tail.head
			val exporter: TextExporter = new FileOutputExporter(path)
			exporterBuilder.registerProperty(exporter)
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
