
import app.converters.AsciiLinearConverter
import app.handlers.importHandlers.{ImportErrorHandler, ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.importers.{ImporterJpg, ImporterPng, PrimitiveImageGenerator}
import app.inputParser.{InputArgumentsParser, InputParser}
import app.models.commands.DefaultCommands
import app.processor.{ImageProcessor, ImageProcessorImpl}

import scala.io.Source
import java.io.{File, PrintStream}
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

object Main {

	val outStream = new PrintStream(System.out)
	val commands = new DefaultCommands

	def main(args: Array[String]): Unit = {


		val inputParser = new InputArgumentsParser(args, commands)

		val imageProcessor = new ImageProcessorImpl(new AsciiLinearConverter)
		// filter args

		// call import handler

		// change image to greyscale (can be as a filter at the beginning)

		// call filter handler
		// or
		// call filters (from image processor)

		// call image converter

		// call export handler

		importHandlers(imageProcessor).handle(inputParser.getImageSource)
	}

	def importHandlers(imageProcessor: ImageProcessor): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(new ImporterJpg, imageProcessor, commands)
		val importPngHandler = new ImportPngHandler(new ImporterPng, imageProcessor, commands)
		val importRandomHandler = new ImportRandomHandler(new PrimitiveImageGenerator, imageProcessor, commands)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)
		  .setNext(new ImportErrorHandler(outStream))

		initialImportHandler
	}
}