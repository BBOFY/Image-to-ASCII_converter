package app.handlers

import app.builders.FilterBuilder
import app.filters.ImageFilter
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler, FlipXFilterHandler, FlipYFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.inputParser.InputArgumentsParser

class FilterHandlersTests extends HandlerTests {

	class DummyFilterBuilder extends FilterBuilder {

		var counter = 0
		var filters: Seq[ImageFilter] = Seq.empty
		override def registerProperty(filter: ImageFilter): Unit = {
			counter = counter + 1
			filters = filters.appended(filter)
		}
	}

	private val builder = new DummyFilterBuilder

	private val args0: Seq[String] = Seq("--table", "constant")
	private val args1: Seq[String] = Seq("--table", "bourke", "--custom-table", "abcd")
	private val args2: Seq[String] = Seq("--table", "bourke", "--custom-table")
	private val args3: Seq[String] = Seq("--custom-table", "whatever", "command")
	private val args4: Seq[String] = Seq("--table", "constant")
	private val args5: Seq[String] = Seq("--table", "constant")
	private val args6: Seq[String] = Seq("--table", "constant")


	private def filterHandlers(parser: InputArgumentsParser): FilterHandler = {

		val brightnessFilterHandler = new BrightnessFilterHandler(builder, parser)
		val rotateFilterHandler = new RotateFilterHandler(builder, parser)
		val flipXFilterHandler = new FlipXFilterHandler(builder, parser)
		val flipYFilterHandler = new FlipYFilterHandler(builder, parser)
		val invertFilterHandler = new InvertFilterHandler(builder, parser)

		val initialFilterHandler: FilterHandler = brightnessFilterHandler
		brightnessFilterHandler
		  .setNext(rotateFilterHandler)
		  .setNext(flipXFilterHandler)
		  .setNext(flipYFilterHandler)
		  .setNext(invertFilterHandler)

		initialFilterHandler
	}
}
