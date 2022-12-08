
import app.builders.{AsciiConversionBuilder, ExporterBuilder, FilterBuilder}
import app.handlers.converterHandlers.{BourkeConverterHandler, ConstantConverterHandler, ConverterHandler, CustomConverterHandler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler, FlipXFilterHandler, FlipYFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.handlers.importHandlers.{ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.processor.{ImageProcessor, ImageProcessorImpl}

object Main {

	def main(args: Array[String]): Unit = {

		val inputParser = new InputArgumentsParser(args)
		val imageProcessor = new ImageProcessorImpl

		val filterBuilder = new FilterBuilder
		val conversionBuilder = new AsciiConversionBuilder
		val exporterBuilder = new ExporterBuilder

		val imageFilter = filterBuilder.build
		val imageConvrter = conversionBuilder.build
		val imageExporter = exporterBuilder.build

		imageProcessor.activatePipeline(
			imageFilter,
			imageConvrter,
			imageExporter
		)

	}

	def importHandlers(imageProcessor: ImageProcessor, parser: InputArgumentsParser): ImportHandler = {

		val importJpgHandler = new ImportJpgHandler(imageProcessor, parser)
		val importPngHandler = new ImportPngHandler(imageProcessor, parser)
		val importRandomHandler = new ImportRandomHandler(imageProcessor, parser)

		val initialImportHandler: ImportHandler = importJpgHandler
		initialImportHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)

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
		  .setNext(customConverterHandler)

		initialConverterHandler
	}
}