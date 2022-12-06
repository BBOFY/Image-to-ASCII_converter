package app.handlers.filterHandlers

import app.builders.FilterBuilder
import app.filters.ImageFilter
import app.filters.specific.BrightnessFilter
import app.handlers.Handler
import app.models.commands.Commands

class BrightnessFilterHandler(val filterBuilder: FilterBuilder, val cmds: Commands) extends FilterHandler {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {

		if (args.head == cmds.cmdFilterBright) {
			filterBuilder.registerProperty(new BrightnessFilter(args.apply(1).toInt))
			None
		}
		else nextHandler

	}

}
