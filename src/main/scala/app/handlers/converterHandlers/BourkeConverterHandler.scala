package app.handlers.converterHandlers

import app.builders.AsciiConversionBuilder
import app.handlers.Handler
import app.inputParser.InputParser
import app.inputParser.commands.Commands
import app.models.conversionTables.{BourkeTable, CustomTable}
import app.processor.ImageProcessorImpl

class BourkeConverterHandler(val builder: AsciiConversionBuilder, val parser: InputParser[String], val cmds: Commands) extends ConverterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		if (args.head == cmds.cmdTable && args.apply(1) == "bourke") {
			builder.registerProperty(new BourkeTable(255))
			parser.removeElements(2)
			None
		}
		else nextHandler
	}
}
