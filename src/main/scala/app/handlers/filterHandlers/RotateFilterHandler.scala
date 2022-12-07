package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
import app.filters.specific.{RotateFilter, VariableFilter}
import app.handlers.Handler
import app.inputParser.InputParser

class RotateFilterHandler(val filterBuilder: FilterBuilder,
						  val parser: InputParser[String],
						  val filter: VariableFilter = new RotateFilter
						 )
  extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == Commands.filterRotate) {
			filter.setValue(args.apply(1).toInt)
			filterBuilder.registerProperty(filter)
			parser.removeElements(2)
			None
		}
		else nextHandler

	}
}
