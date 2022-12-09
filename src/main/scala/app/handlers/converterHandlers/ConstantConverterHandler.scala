package app.handlers.converterHandlers

import app.builders.AsciiConversionBuilder
import app.enums.{Commands, Tables}
import app.inputParser.InputParser
import app.models.conversionTables.ConstantTable
import handler.Handler

class ConstantConverterHandler(val builder: AsciiConversionBuilder,
							   val parser: InputParser[String],
							   val table: ConstantTable = ConstantTable()
							  )
  extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.nonEmpty
		  && args.tail.nonEmpty
		  && args.head == Commands.table.toString
		  && args.tail.head == Tables.conversionConstant.toString) {
			builder.registerProperty(table)
			parser.removeElements(2)
			None
		}
		else super.handle(args)
	}
}
