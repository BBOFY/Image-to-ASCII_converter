package app.handlers.converterHandlers

import app.builders.AsciiConversionBuilder
import app.enums.Commands
import app.inputParser.InputParser
import app.models.conversionTables.CustomTable
import handler.Handler

class CustomConverterHandler(val builder: AsciiConversionBuilder,
							 val parser: InputParser[String],
							 val conversionTable: CustomTable = CustomTable()
							)
  extends ConverterHandler {

	/**
	 *  Checks, if args contain command for custom table and for its definition by user
	 *  @param args Arguments to choose from to handle
	 *  @return The next handler, or none if argument is found and correctly processed or args are empty
	 */
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.tableCustom.toString)
			return super.handle(args)

		if (args.tail.isEmpty)
			return None

		val table = args.tail.head
		conversionTable.setValue(table)
		builder.registerProperty(conversionTable)
		parser.removeElements(2)
		None

	}
}
