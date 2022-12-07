package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.handlers.Handler
import app.inputParser.InputParser
import app.models.conversionTables.CustomTable

class CustomConverterHandler(val builder: AsciiConversionBuilder, val parser: InputParser[String]) extends ConverterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == Commands.tableCustom) {
			builder.registerProperty(new CustomTable(255, args.apply(1)))
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
