
import app.handlers.ImportHandler
import app.handlers.importHandlers.{ImportErrorHandler, ImportJpgHandler, ImportPngHandler}
import app.processor.ImageProcessorImpl

import scala.io.Source
import java.io.{File, PrintStream}
import javax.imageio.ImageIO
import java.awt.Image
import java.awt.image.BufferedImage

object Main {
	private val imageProcessor = ImageProcessorImpl
	private val outStream = new PrintStream(System.out)

	def main(args: Array[String]): Unit = {



		// filter args

		// call import handler

		// change image to greyscale (can be as a filter at the beginning)

		// call filter handler
		// or
		// call filters (from image processor)

		// call image converter

		// call export handler

	}

	def importHandlers(): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(imageProcessor)
		val importPngHandler = new ImportPngHandler(imageProcessor)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(new ImportErrorHandler(outStream))

		initialImportHandler
	}
}