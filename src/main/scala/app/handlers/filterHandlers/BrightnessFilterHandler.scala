package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
import app.filters.specific.BrightnessFilter
import app.handlers.Handler
import app.inputParser.InputParser

class BrightnessFilterHandler(val filterBuilder: FilterBuilder,
							  val parser: InputParser[String],
							  val filter: BrightnessFilter = new BrightnessFilter
							 )
  extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == Commands.filterBright) {
			filter.setValue(args.apply(1).toInt)
			filterBuilder.registerProperty(filter)
			parser.removeElements(2)
			None
		}
		else nextHandler

	}

}
