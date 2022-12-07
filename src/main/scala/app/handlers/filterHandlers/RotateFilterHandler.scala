package app.handlers.filterHandlers

import app.builders.FilterBuilder
import app.filters.specific.RotateFilter
import app.handlers.Handler
import app.inputParser.InputParser
import app.inputParser.commands.Commands

class RotateFilterHandler(val filterBuilder: FilterBuilder,
						  val parser: InputParser[String],
						  val cmds: Commands)
  extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == cmds.cmdFilterBright) {
			filterBuilder.registerProperty(new RotateFilter(args.apply(1).toInt))
			parser.removeElements(2)
			None
		}
		else nextHandler

	}
}
