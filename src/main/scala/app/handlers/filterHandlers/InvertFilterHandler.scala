package app.handlers.filterHandlers

import app.builders.FilterBuilder
import app.filters.specific.InvertFilter
import app.handlers.Handler
import app.inputParser.InputParser
import app.inputParser.commands.Commands

class InvertFilterHandler(val filterBuilder: FilterBuilder,
						  val parser: InputParser[String],
						  val cmds: Commands)
	extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == cmds.cmdFilterBright) {
			filterBuilder.registerProperty(new InvertFilter)
			parser.removeElements(1)
			None
		}
		else nextHandler

	}
}
