package app.handlers

import app.builders.FilterBuilder
import app.filters.ImageFilter
import app.filters.specific.{BrightnessFilter, FlipXFilter, FlipYFilter, InvertFilter, RotateFilter}
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

	private val args0: Seq[String] = Seq("--invert", "--flip", "x")
	private val args1: Seq[String] = Seq("--rotate", "+90", "--rotate", "-270")
	private val args2: Seq[String] = Seq("--brightness", "+84", "--flip", "y", "--flip", "y")
	private val args3: Seq[String] = Seq("--invert", "whatever")
	private val args4: Seq[String] = Seq("--flip", "x", "y")
	private val args5: Seq[String] = Seq("--rotate", "91", "--invert")
	private val args6: Seq[String] = Seq("--invert", "--flip", "--invert")
	private val args7: Seq[String] = Seq("--brightness", "p")

	private val rotateFilter90 = new RotateFilter
	rotateFilter90.setValue(90)
	private val rotateFilter_270 = new RotateFilter
	rotateFilter_270.setValue(-270)
	private val brightFilter84 = new BrightnessFilter
	brightFilter84.setValue(84)

	private val refFilters0: Seq[ImageFilter] = Seq(new InvertFilter, new FlipXFilter)
	private val refFilters1: Seq[ImageFilter] = Seq(rotateFilter90, rotateFilter_270)
	private val refFilters2: Seq[ImageFilter] = Seq(brightFilter84, new FlipYFilter, new FlipYFilter)
	private val refFilters3: Seq[ImageFilter] = Seq(new InvertFilter)
	private val refFilters4: Seq[ImageFilter] = Seq(new FlipXFilter)
	private val refFilters5: Seq[ImageFilter] = Seq.empty
	private val refFilters6: Seq[ImageFilter] = Seq(new InvertFilter)
	private val refFilters7: Seq[ImageFilter] = Seq.empty


	test("Valid input 0") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args0)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 2)
		assert(builder.filters == refFilters0)
	}
	test("Valid input 1") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args1)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 2)
		assert(builder.filters == refFilters1)
	}
	test("Valid input 2") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args2)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 3)
		assert(builder.filters == refFilters2)
	}
	test("Input with unknown command") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args3)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("whatever"))
		assert(builder.counter == 1)
		assert(builder.filters == refFilters3)
	}
	test("Input with too many arguments") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args4)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("y"))
		assert(builder.counter == 1)
		assert(builder.filters == refFilters4)
	}
	test("Input with invalid rotation") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args5)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("91", "--invert"))
		assert(builder.counter == 0)
		assert(builder.filters == refFilters5)
	}
	test("Input with missing argument rotation") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args6)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("--invert"))
		assert(builder.counter == 1)
		assert(builder.filters == refFilters6)
	}
	test("Input with invalid argument") {
		builder.counter = 0
		builder.filters = Seq.empty
		val parser = new InputArgumentsParser(args7)
		val handlers = filterHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("p"))
		assert(builder.counter == 0)
		assert(builder.filters == refFilters7)
	}


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
