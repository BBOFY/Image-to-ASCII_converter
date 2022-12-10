package app.handlers.filterHandlers

import app.builders.FilterBuilder
import app.enums.{Axes, Commands}
import app.filters.ImageFilter
import app.filters.specific.{FlipXFilter, FlipYFilter}
import app.inputParser.InputParser
import handler.Handler

class FlipFilterHandler(val filterBuilder: FilterBuilder,
						val parser: InputParser[String],
						val flipperX: ImageFilter = new FlipXFilter,
						val flipperY: ImageFilter = new FlipYFilter
					   )
  extends FilterHandler {
	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.isEmpty)
			return None

		if (args.head != Commands.filterFlip.toString)
			return super.handle(args)

		if (args.tail.isEmpty
		  || Commands.isCommand(args.tail.head))
			return None

		if (!Axes.isAxis(args.tail.head)) {
			parser.removeElements(1)
			return None
		}

		if (args.tail.head == Axes.x.toString)
			filterBuilder.registerProperty(flipperX)
		else
			filterBuilder.registerProperty(flipperY)
		parser.removeElements(2)
		None

	}
}