package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
import app.filters.specific.InvertFilter
import app.handlers.Handler
import app.inputParser.InputParser

class InvertFilterHandler(val filterBuilder: FilterBuilder,
						  val parser: InputParser[String],
						  val filter: InvertFilter = new InvertFilter
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
