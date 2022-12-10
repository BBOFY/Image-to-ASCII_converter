
import app.builders.{AsciiConversionBuilder, ExporterBuilder, FilterBuilder}
import app.handlers.CommandHandler
import app.handlers.converterHandlers.{BourkeConverterHandler, ConstantConverterHandler, ConverterHandler, CustomConverterHandler}
import app.handlers.exportHandlers.{ExportHandler, FileOutputHandler, StdOutputHandler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FilterHandler, FlipFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.handlers.importHandlers.{ImportHandler, ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.processor.{ImageProcessor, ImageProcessorImpl}
import exporter.text.StdOutputExporter
import handler.Handler

object Main {

	def main(args: Array[String]): Unit = {

		val stdOutput = new StdOutputExporter

		val argsParser = new InputArgumentsParser(args.toSeq)

		try {
			argsParser.checkValidity()
		}
		catch {
			case e: IllegalArgumentException => stdOutput.`export`(e.getMessage)
			case _: Throwable => stdOutput.`export`("Unknown error")
		}

		val imageProcessor = new ImageProcessorImpl

		val filterBuilder = new FilterBuilder
		val conversionBuilder = new AsciiConversionBuilder
		val exporterBuilder = new ExporterBuilder

		val filterHandler = filterHandlers(filterBuilder, argsParser)
		val importHandler = importHandlers(imageProcessor, argsParser)
		val converterHandler = converterHandlers(conversionBuilder, argsParser)
		val exporterHandler = exporterHandlers(exporterBuilder, argsParser)

		callArgs(importHandler, argsParser)
		callArgs(filterHandler, argsParser)
		callArgs(converterHandler, argsParser)
		callArgs(exporterHandler, argsParser)

		if (argsParser.getArgs.nonEmpty) {
			stdOutput.`export`(s"Unknown command or invalid argument: '${argsParser.getArgs.head}'\n")
			return
		}

		val imageFilter = filterBuilder.build
		val imageConverter = conversionBuilder.build
		val imageExporters = exporterBuilder.build


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
		val flipFilterHandler = new FlipFilterHandler(filterBuilder, parser)
		val invertFilterHandler = new InvertFilterHandler(filterBuilder, parser)

		val initialFilterHandler: FilterHandler = brightnessFilterHandler
		brightnessFilterHandler
		  .setNext(rotateFilterHandler)
		  .setNext(flipFilterHandler)
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
