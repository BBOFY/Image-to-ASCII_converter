
import app.builders.FilterBuilder
import app.converters.AsciiLinearConverter
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler}
import app.handlers.importHandlers.{ImportErrorHandler, ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.importers.{ImporterJpg, ImporterPng, PrimitiveImageGenerator}
import app.inputParser.commands.DefaultCommands
import app.inputParser.{InputArgumentsParser, InputParser}
import app.processor.{ImageProcessor, ImageProcessorImpl}

import scala.io.Source
import java.io.{File, PrintStream}
import javax.imageio.ImageIO
import java.awt.image.BufferedImage

object Main {

	val outStream = new PrintStream(System.out)
	val commands = new DefaultCommands

	def main(args: Array[String]): Unit = {

		// filter args
		val inputParser = new InputArgumentsParser(args, commands)

		// todo builder for converter, similarly as filters
		val imageProcessor = new ImageProcessorImpl(new AsciiLinearConverter)
		val filterBuilder = new FilterBuilder

		// call import handler
		importHandlers(imageProcessor).handle(inputParser.getImageSource)

		// change image to greyscale (can be as a filter at the beginning)
		imageProcessor.greyScaleImage()


		// sort args into pairs on as singles (defined by command) in input parser
		// create mixed filter with parsed args and insert it to image processor
		// call input handler
		// call greyscale conversion
		// call image processor's filtering
		// call conversion specified in args
		// call export handlers, image processor should only pass done image to exporters via handler

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

	def filterHandlers(filterBuilder: FilterBuilder): FilterHandler = {
		val brightnessFilterHandler = new BrightnessFilterHandler(filterBuilder, commands)

		val initialFilterHandler: FilterHandler = brightnessFilterHandler

		initialFilterHandler
	}
}