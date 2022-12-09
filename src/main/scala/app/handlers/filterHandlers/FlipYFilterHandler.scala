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

		if (args.isEmpty)
			return None

		if (args.head != Commands.filterFlip.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || !Commands.isCommand(args.tail.head))
			return None

		if (!args.tail.head.matches(Commands.axisY.toString)) {
			parser.removeElements(1)
			return None
		}

		filterBuilder.registerProperty(filter)
		parser.removeElements(2)
		None

	}
}
