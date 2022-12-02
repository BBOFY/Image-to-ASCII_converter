package app.importers

import app.handlers.{Handler, ImportHandler}
import app.handlers.importHandlers.{ImportErrorHandler, ImportJpgHandler, ImportPngHandler}
import app.processor.ImageProcessorImpl
import org.scalatest.FunSuite

class ImporterHandlerTests extends FunSuite {
	private val imageProcessor = ImageProcessorImpl
	private val outStream = System.out

	private def importHandlers(): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(imageProcessor)
		val importPngHandler = new ImportPngHandler(imageProcessor)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(new ImportErrorHandler(outStream))

		initialImportHandler
	}

	test("") {
		val importHandler: ImportHandler = importHandlers()
		Handler.handleAll(importHandler, "src/main/resources/test_png.png")
		val img = imageProcessor.getImage()
		for (y <- 0 until img.height) {
			for (x <- 0 until img.width) {
				println(img.getPixel(x, y))
			}
			println()
		}

	}
}
