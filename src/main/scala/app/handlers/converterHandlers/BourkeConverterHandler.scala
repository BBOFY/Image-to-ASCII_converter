package app.handlers.converterHandlers

import app.builders.AsciiConversionBuilder
import app.enums.{Commands, Tables}
import app.inputParser.InputParser
import app.models.conversionTables.BourkeTable
import handler.Handler

class BourkeConverterHandler(val builder: AsciiConversionBuilder,
							 val parser: InputParser[String],
							 val table: BourkeTable = BourkeTable()
							)
  extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.table.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || Commands.isCommand(args.tail.head))
			return None

		if (!Tables.isTable(args.tail.head)) {
			parser.removeElements(1)
			return None
		}

		if (args.tail.head == Tables.conversionBourke.toString) {
			builder.registerProperty(table)
			parser.removeElements(2)
			None
		}
		else super.handle(args)

	}
}
