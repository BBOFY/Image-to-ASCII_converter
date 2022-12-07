package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.handlers.Handler
import app.inputParser.InputParser
import app.models.conversionTables.CustomTable

class CustomConverterHandler(val builder: AsciiConversionBuilder,
							 val parser: InputParser[String],
							 val conversionTable: CustomTable = new CustomTable(255)
							)
  extends ConverterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == Commands.tableCustom) {
			val table = args.apply(1)
			conversionTable.setValue(table)
			builder.registerProperty(conversionTable)
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
