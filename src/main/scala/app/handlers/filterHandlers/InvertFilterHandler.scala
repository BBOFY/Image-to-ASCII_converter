package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
import app.filters.ImageFilter
import app.filters.specific.InvertFilter
import app.inputParser.InputParser
import handler.Handler

class InvertFilterHandler(val filterBuilder: FilterBuilder,
						  val parser: InputParser[String],
						  val filter: ImageFilter = new InvertFilter
						 )
	extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == Commands.filterInv) {
			filterBuilder.registerProperty(filter)
			parser.removeElements(1)
			None
		}
		else nextHandler

	}
}
