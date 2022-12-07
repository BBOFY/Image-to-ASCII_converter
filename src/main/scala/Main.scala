
import app.builders.FilterBuilder
import app.converters.{AsciiConverter, GreyScaler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler, FlipFilterHandler, InvertFilterHandler, RotateFilterHandler}
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

		val asciiConverter = new AsciiConverter

		// todo builder for converter, similarly as filters
		val imageProcessor = new ImageProcessorImpl
		val filterBuilder = new FilterBuilder


		val imageFilter = filterBuilder.build


		// call import handler
//		importHandlers(imageProcessor).handle(inputParser.getImageSource)

		// change image to greyscale (can be as a filter at the beginning)



		// sort args into pairs on as singles (defined by command) in input parser
		// create mixed filter with parsed args and insert it to image processor
		// call input handler
		// call greyscale conversion
		// call image processor's filtering
		// call conversion specified in args
		// call export handlers, image processor should only pass done image to exporters via handler

		imageProcessor.greyScaleImage(new GreyScaler)
		imageProcessor.filterImage(imageFilter)
		imageProcessor.convertImage(asciiConverter)

	}

	def importHandlers(imageProcessor: ImageProcessor, parser: InputArgumentsParser): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(new ImporterJpg, imageProcessor, parser, commands)
		val importPngHandler = new ImportPngHandler(new ImporterPng, imageProcessor, parser, commands)
		val importRandomHandler = new ImportRandomHandler(new PrimitiveImageGenerator, imageProcessor, parser, commands)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)
		  .setNext(new ImportErrorHandler(outStream))

		initialImportHandler
	}

	def filterHandlers(filterBuilder: FilterBuilder, parser: InputArgumentsParser): FilterHandler = {
		val brightnessFilterHandler = new BrightnessFilterHandler(filterBuilder, parser, commands)
		val rotateFilterHandler = new RotateFilterHandler(filterBuilder, parser, commands)
		val flipFilterHandler = new FlipFilterHandler(filterBuilder, parser, commands)
		val invertFilterHandler = new InvertFilterHandler(filterBuilder, parser, commands)

		val initialFilterHandler: FilterHandler = brightnessFilterHandler
		brightnessFilterHandler
		  .setNext(rotateFilterHandler)
		  .setNext(flipFilterHandler)
		  .setNext(invertFilterHandler)

		initialFilterHandler
	}
}