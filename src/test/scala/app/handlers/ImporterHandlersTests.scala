package app.handlers

import app.handlers.importHandlers.{ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.models.pixel.PixelRgb

class ImporterHandlersTests extends HandlerTests {

	private val args0: Seq[String] = Seq("--image", "src/main/resources/test.png")
	private val args1: Seq[String] = Seq("--image-random", "whatever", "--image-random")
	private val args2: Seq[String] = Seq("--image", "src/main/resources/test.gif", "--image", "src/main/resources/test.jpg", "--image")
	private val args3: Seq[String] = Seq("--image", "src/main/resources/test.jpg", "--image-random", "--image", "src/main/resources/test.gif")

	private val imageProcessor = new DummyProcessor

	test("Simple input") {
		val parser = new InputArgumentsParser(args0)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(imageProcessor.counter == 1)
		assert(imageProcessor.image.getGrid == Vector(
			Vector(PixelRgb(255, 0, 0), PixelRgb(0, 255, 0), PixelRgb(0, 0, 255), PixelRgb(255, 255, 255)),
			Vector(PixelRgb(255, 255, 0), PixelRgb(255, 0, 255), PixelRgb(0, 255, 255), PixelRgb(255, 255, 255)),
			Vector(PixelRgb(0, 0, 0), PixelRgb(63, 63, 63), PixelRgb(127, 127, 127), PixelRgb(191, 191, 191))
		))
	}

	test("Simple input with invalid command") {
		val parser = new InputArgumentsParser(args1)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("whatever", "--image-random"))
		assert(imageProcessor.counter == 1)
		assert(imageProcessor.image.getGrid == Vector(Vector(PixelRgb(42, 6, 9))))
	}

	test("Input with unsupported extension") {
		val parser = new InputArgumentsParser(args2)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == args2)
		assert(imageProcessor.counter == 0)

	}

	test("Input partially correct") {
		val parser = new InputArgumentsParser(args3)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("--image", "src/main/resources/test.gif"))
		assert(imageProcessor.counter == 2)

	}

	private def importHandlers(parser: InputArgumentsParser): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(imageProcessor, parser)
		val importPngHandler = new ImportPngHandler(imageProcessor, parser)
		val importRandomHandler = new ImportRandomHandler(imageProcessor, parser)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)

		initialImportHandler
	}
}
