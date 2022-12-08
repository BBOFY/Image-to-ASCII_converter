package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.inputParser.InputParser
import app.models.conversionTables.BourkeTable
import handler.Handler

class BourkeConverterHandler(val builder: AsciiConversionBuilder,
							 val parser: InputParser[String],
							 val table: BourkeTable = BourkeTable()
							)
  extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.nonEmpty
		  && args.tail.nonEmpty
		  && args.head == Commands.table
		  && args.tail.head == Commands.conversionBourke) {
			builder.registerProperty(table)
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
