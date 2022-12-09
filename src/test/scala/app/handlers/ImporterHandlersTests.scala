package app.handlers

import app.handlers.importHandlers.{ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb
import app.processor.ImageProcessorImpl

class ImporterHandlersTests extends HandlerTests {

	class DummyProcessor extends ImageProcessorImpl {
		var image: ImageRgb = _importedImage
		var counter = 0

		override def loadImage(img: ImageRgb): Unit = {
			counter = counter + 1
			image = img
		}
	}

	private val args0: Seq[String] = Seq("--image", "src/main/resources/test.png")
	private val args1: Seq[String] = Seq("--image-random", "whatever", "--image-random")
	private val args2: Seq[String] = Seq("--image", "src/main/resources/test.gif", "--image", "src/main/resources/test.jpg", "--image")
	private val args3: Seq[String] = Seq("--image", "src/main/resources/test.jpg", "--image-random", "--image", "src/main/resources/test.gif")
	private val args4: Seq[String] = Seq("--image", "src/main/resources/nonExistent.png")
	private val args5: Seq[String] = Seq("--image", "src/main/resources/nonExistent.jpg")

	private val processor = new DummyProcessor

	test("Simple input") {
		processor.counter = 0
		val parser = new InputArgumentsParser(args0)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(processor.counter == 1)
		assert(processor.image.getGrid == Vector(
			Vector(PixelRgb(255, 0, 0), PixelRgb(0, 255, 0), PixelRgb(0, 0, 255), PixelRgb(255, 255, 255)),
			Vector(PixelRgb(255, 255, 0), PixelRgb(255, 0, 255), PixelRgb(0, 255, 255), PixelRgb(255, 255, 255)),
			Vector(PixelRgb(0, 0, 0), PixelRgb(63, 63, 63), PixelRgb(127, 127, 127), PixelRgb(191, 191, 191))
		))
	}

	test("Simple input with invalid command") {
		processor.counter = 0
		val parser = new InputArgumentsParser(args1)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("whatever", "--image-random"))
		assert(processor.counter == 1)
		assert(processor.image.getGrid == Vector(Vector(PixelRgb(42, 6, 9))))
	}

	test("Input with unsupported extension") {
		processor.counter = 0
		val parser = new InputArgumentsParser(args2)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == args2)
		assert(processor.counter == 0)
	}

	test("Input partially correct") {
		processor.counter = 0
		val parser = new InputArgumentsParser(args3)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("--image", "src/main/resources/test.gif"))
		assert(processor.counter == 2)
	}

	test("Not existing png") {
		processor.counter = 0
		val parser = new InputArgumentsParser(args4)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("--image", "src/main/resources/nonExistent.png"))
		assert(processor.counter == 0)
	}
	test("Not existing jpg") {
		processor.counter = 0
		val parser = new InputArgumentsParser(args5)
		val handlers = importHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("--image", "src/main/resources/nonExistent.jpg"))
		assert(processor.counter == 0)
	}


	private def importHandlers(parser: InputArgumentsParser): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(processor, parser)
		val importPngHandler = new ImportPngHandler(processor, parser)
		val importRandomHandler = new ImportRandomHandler(processor, parser)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)

		initialImportHandler
	}
}
