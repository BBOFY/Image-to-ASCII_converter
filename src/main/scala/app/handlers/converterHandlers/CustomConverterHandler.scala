package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.inputParser.InputParser
import app.models.conversionTables.CustomTable
import handler.Handler

class CustomConverterHandler(val builder: AsciiConversionBuilder,
							 val parser: InputParser[String],
							 val conversionTable: CustomTable = CustomTable()
							)
  extends ConverterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.nonEmpty
		  && args.tail.nonEmpty
		  && args.head == Commands.tableCustom) {
			val table = args.tail.head
			conversionTable.setValue(table)
			builder.registerProperty(conversionTable)
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
