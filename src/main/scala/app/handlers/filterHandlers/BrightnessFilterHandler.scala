package app.handlers.filterHandlers

import app.builders.FilterBuilder
import app.enums.Commands
import app.filters.VariableFilter
import app.filters.specific.BrightnessFilter
import app.inputParser.InputParser
import handler.Handler

class BrightnessFilterHandler(val filterBuilder: FilterBuilder,
							  val parser: InputParser[String],
							  val filter: VariableFilter = new BrightnessFilter
							 )
  extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.filterBright.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || !args.tail.head.matches( "^[+-]?\\d+$")
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
