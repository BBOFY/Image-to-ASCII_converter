package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.inputParser.InputParser
import app.models.conversionTables.ConstantTable
import handler.Handler

class ConstantConverterHandler(val builder: AsciiConversionBuilder,
							   val parser: InputParser[String],
							   val table: ConstantTable = new ConstantTable(255)
							  )
  extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == Commands.table && args.apply(1) == Commands.conversionConstant) {
			builder.registerProperty(table)
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
