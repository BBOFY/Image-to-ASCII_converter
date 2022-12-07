package app.handlers.converterHandlers

import app.Commands
import app.builders.AsciiConversionBuilder
import app.handlers.Handler
import app.inputParser.InputParser
import app.models.conversionTables.ConstantTable

class ConstantConverterHandler(val builder: AsciiConversionBuilder,
							   val parser: InputParser[String]
							  )
  extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == Commands.table && args.apply(1) == Commands.conversionConstant) {
			builder.registerProperty(new ConstantTable(255))
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
