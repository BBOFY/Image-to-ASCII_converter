
import app.builders.{AsciiConversionBuilder, FilterBuilder}
import app.converters.GreyScaler
import app.handlers.converterHandlers.{BourkeConverterHandler, ConstantConverterHandler, ConverterHandler, CustomConverterHandler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler, FlipXFilterHandler, FlipYFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.handlers.importHandlers.{ImportErrorHandler, ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.importers.{ImporterJpg, ImporterPng, PrimitiveImageGenerator}
import app.inputParser.InputArgumentsParser
import app.processor.{ImageProcessor, ImageProcessorImpl}

import java.io.PrintStream

object Main {

	val vec: Vector[Int] = Vector(1, 2, 3)


	val outStream = new PrintStream(System.out)

	def main(args: Array[String]): Unit = {

		// filter args

		val inputParser = new InputArgumentsParser(args)
		val imageProcessor = new ImageProcessorImpl

		val conversionBuilder = new AsciiConversionBuilder
		val filterBuilder = new FilterBuilder

		// todo builder for converter, similarly as filters


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


		val converter = conversionBuilder.build

		imageProcessor.greyScaleImage(new GreyScaler)
		imageProcessor.filterImage(imageFilter)
		imageProcessor.convertImage(converter)


	}

	def importHandlers(imageProcessor: ImageProcessor, parser: InputArgumentsParser): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(imageProcessor, parser)
		val importPngHandler = new ImportPngHandler(imageProcessor, parser)
		val importRandomHandler = new ImportRandomHandler(imageProcessor, parser)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)
		  .setNext(new ImportErrorHandler(outStream))

		initialImportHandler
	}

	def filterHandlers(filterBuilder: FilterBuilder, parser: InputArgumentsParser): FilterHandler = {
		val brightnessFilterHandler = new BrightnessFilterHandler(filterBuilder, parser)
		val rotateFilterHandler = new RotateFilterHandler(filterBuilder, parser)
		val flipXFilterHandler = new FlipXFilterHandler(filterBuilder, parser)
		val flipYFilterHandler = new FlipYFilterHandler(filterBuilder, parser)
		val invertFilterHandler = new InvertFilterHandler(filterBuilder, parser)

		val initialFilterHandler: FilterHandler = brightnessFilterHandler
		brightnessFilterHandler
		  .setNext(rotateFilterHandler)
		  .setNext(flipXFilterHandler)
		  .setNext(flipYFilterHandler)
		  .setNext(invertFilterHandler)

		initialFilterHandler
	}

	def converterHandlers(converterBuilder: AsciiConversionBuilder, parser: InputArgumentsParser): ConverterHandler = {
		val bourkeConverterHandler = new BourkeConverterHandler(converterBuilder, parser)
		val constantConverterHandler = new ConstantConverterHandler(converterBuilder, parser)
		val customConverterHandler = new CustomConverterHandler(converterBuilder, parser)

		val initialConverterHandler: ConverterHandler = bourkeConverterHandler
		bourkeConverterHandler
		  .setNext(constantConverterHandler)
		  .setNext(initialConverterHandler)

		initialConverterHandler
	}
}