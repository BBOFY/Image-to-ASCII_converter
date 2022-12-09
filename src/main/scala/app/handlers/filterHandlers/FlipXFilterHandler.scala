package app.handlers.filterHandlers

import app.Commands
import app.builders.FilterBuilder
import app.filters.ImageFilter
import app.filters.specific.FlipXFilter
import app.inputParser.InputParser
import handler.Handler

class FlipXFilterHandler(val filterBuilder: FilterBuilder,
						val parser: InputParser[String],
						val filter: ImageFilter = new FlipXFilter
					   )
  extends FilterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.nonEmpty
		  && args.tail.nonEmpty
		  && args.head == Commands.filterFlip
		  && args.tail.head == Commands.axisX
		) {
			filterBuilder.registerProperty(filter)
			parser.removeElements(2)
			None
		}
		else super.handle(args)

	}
}
