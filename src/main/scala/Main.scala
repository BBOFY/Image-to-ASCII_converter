
import app.builders.{AsciiConversionBuilder, ExporterBuilder, FilterBuilder}
import app.handlers.CommandHandler
import app.handlers.converterHandlers.{BourkeConverterHandler, ConstantConverterHandler, ConverterHandler, CustomConverterHandler}
import app.handlers.exportHandlers.{ExportHandler, FileOutputHandler, StdOutputHandler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler, FlipXFilterHandler, FlipYFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.handlers.importHandlers.{ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.processor.{ImageProcessor, ImageProcessorImpl}
import exporter.text.{FileOutputExporter, StdOutputExporter}
import handler.Handler

object Main {

	def main(args: Array[String]): Unit = {

		val stdOutput = new StdOutputExporter

		val inputParser = new InputArgumentsParser(args.toSeq)

		try {
			inputParser.checkValidity()
		}
		catch {
			case e: IllegalArgumentException => stdOutput.`export`(e.getMessage)
			case _: Throwable => stdOutput.`export`("Unknown error")
		}

		val imageProcessor = new ImageProcessorImpl

		val filterBuilder = new FilterBuilder
		val conversionBuilder = new AsciiConversionBuilder
		val exporterBuilder = new ExporterBuilder

		val importHandler = importHandlers(imageProcessor, inputParser)
		val filterHandler = filterHandlers(filterBuilder, inputParser)
		val converterHandler = converterHandlers(conversionBuilder, inputParser)
		val exporterHandler = exporterHandlers(exporterBuilder, inputParser)

		callArgs(importHandler, inputParser)
		callArgs(filterHandler, inputParser)
		callArgs(converterHandler, inputParser)
		callArgs(exporterHandler, inputParser)


		val imageFilter = filterBuilder.build
		val imageConverter = conversionBuilder.build
		val imageExporters = exporterBuilder.build

		if (!inputParser.argsEmpty()) {
			stdOutput.`export`(s"Unknown command '${inputParser.getArgs.head}'")
			return
		}

		if (imageExporters.isEmpty) {
			stdOutput.`export`("No output specified. Skipping conversion")
			return
		}

		imageProcessor.activatePipeline(
			imageFilter,
			imageConverter,
			imageExporters
		)


	}

	def callArgs(handler: CommandHandler, parser: InputArgumentsParser): Unit = {
		var lastProcessedArgs: List[String] = List.empty
		while (parser.getArgs.nonEmpty && lastProcessedArgs != parser.getArgs) {
			lastProcessedArgs = parser.getArgs
			Handler.handleAll(handler, lastProcessedArgs)
		}
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

	def exporterHandlers(exporterBuilder: ExporterBuilder, parser: InputArgumentsParser): ExportHandler = {
		val stdOutputHandler = new StdOutputHandler(exporterBuilder, parser)
		val fileOutputHandler = new FileOutputHandler(exporterBuilder, parser)

		val initialExporterHandler: ExportHandler = stdOutputHandler
		initialExporterHandler
		  .setNext(fileOutputHandler)
		initialExporterHandler
	}



}
