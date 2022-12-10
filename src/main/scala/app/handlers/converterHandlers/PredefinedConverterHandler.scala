package app.handlers.converterHandlers

import app.builders.AsciiConversionBuilder
import app.enums.{Commands, Tables}
import app.inputParser.InputParser
import app.models.conversionTables.{BourkeTable, ConstantTable}
import handler.Handler

class PredefinedConverterHandler(val builder: AsciiConversionBuilder,
								 val parser: InputParser[String]
							)
  extends ConverterHandler {

	/**
	 * 	Checks, if args contain command for table and for its name as argument
	 * 	@param args Arguments to choose from to handle
	 * 	@return The next handler, or none if argument is found and correctly processed or args are empty
	 */
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
			builder.registerProperty(BourkeTable())
			parser.removeElements(2)
			return None
		}

		if (args.tail.head == Tables.conversionConstant.toString) {
			builder.registerProperty(ConstantTable())
			parser.removeElements(2)
			None
		}

		else super.handle(args)

	}
}
