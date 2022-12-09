package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
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

		if (args.nonEmpty
		  && args.tail.nonEmpty
		  && args.head == Commands.filterRotate
		  && args.tail.head.toInt % 90 == 0
		) {
			filter.setValue(args.tail.head.toInt)
			filterBuilder.registerProperty(filter)
			parser.removeElements(2)
			None
		}
		else super.handle(args)

	}
}
