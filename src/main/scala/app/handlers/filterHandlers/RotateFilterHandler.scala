package app.handlers.filterHandlers

import app.builders.FilterBuilder
import app.enums.Commands
import app.filters.VariableFilter
import app.filters.specific.RotateFilter
import app.inputParser.InputParser
import handler.Handler

class RotateFilterHandler(val filterBuilder: FilterBuilder,
						  val parser: InputParser[String],
						  val filter: VariableFilter = new RotateFilter
						 )
  extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.filterRotate.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || !args.tail.head.matches("^[+-]?\\d+$")
		  || args.tail.head.toInt % 90 != 0
		) {
			parser.removeElements(1)
			return None
		}

		filter.setValue(args.tail.head.toInt)
		filterBuilder.registerProperty(filter)
		parser.removeElements(2)
		None

	}
}
