package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.handlers.Handler
import app.inputParser.InputParser
import app.models.conversionTables.BourkeTable

class BourkeConverterHandler(val builder: AsciiConversionBuilder,
							 val parser: InputParser[String]
							)
  extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == Commands.table && args.apply(1) == Commands.conversionBourke) {
			builder.registerProperty(new BourkeTable(255))
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
