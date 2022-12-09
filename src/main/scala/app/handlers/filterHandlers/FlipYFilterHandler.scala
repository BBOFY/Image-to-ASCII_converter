package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
import app.filters.ImageFilter
import app.filters.specific.FlipYFilter
import app.inputParser.InputParser
import handler.Handler

class FlipYFilterHandler(val filterBuilder: FilterBuilder,
						 val parser: InputParser[String],
						 val filter: ImageFilter = new FlipYFilter
					   )
  extends FilterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == Commands.filterFlip && args.apply(1) == Commands.axisY) {
			filterBuilder.registerProperty(filter)
			parser.removeElements(2)
			None
		}
		else super.handle(args)

	}
}
