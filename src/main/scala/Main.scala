
import app.builders.{AsciiConversionBuilder, ExporterBuilder, FilterBuilder}
import app.handlers.CommandHandler
import app.handlers.converterHandlers.{BourkeConverterHandler, ConstantConverterHandler, CustomConverterHandler}
import app.handlers.exportHandlers.{FileOutputHandler, StdOutputHandler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FlipFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.handlers.importHandlers.{ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.processor.{ImageProcessor, ImageProcessorImpl}
import exporter.text.StdOutputExporter
import handler.Handler

import scala.language.postfixOps

object Main {

	def main(args: Array[String]): Unit = {

		// Class for one-way communication with the user
		val stdOutput = new StdOutputExporter

		// Creates arguments parser
		val argsParser = new InputArgumentsParser(args.toSeq)

		// Check validity of arguments and
		try {
			argsParser.checkValidity()
		}
		catch {
			case e: IllegalArgumentException => stdOutput.`export`(e.getMessage)
			case _: Throwable => stdOutput.`export`("Unknown error\n")
		}

		// Creates builder called by respective handlers
		val imageProcessor = new ImageProcessorImpl
		val filterBuilder = new FilterBuilder
		val conversionBuilder = new AsciiConversionBuilder
		val exporterBuilder = new ExporterBuilder

		// Creates handle chain for reading arguments
		val commandHandler =
			handlers(
				imageProcessor,
				filterBuilder,
				conversionBuilder,
				exporterBuilder,
				argsParser
			)

		// Calls handle chain
		callArgs(commandHandler, argsParser)

		// Checks, if
		if (argsParser.getArgs.nonEmpty) {
			stdOutput.`export`(s"Unknown command or invalid argument: '${argsParser.getArgs.head}'\n")
			return
		}

		val imageFilter = filterBuilder.build
		val imageConverter = conversionBuilder.build
		val imageExporters = exporterBuilder.build

		if (imageExporters.isEmpty) {
			stdOutput.`export`("No output specified. Skipping conversion\n")
			return
		}

		// Start the whole conversion process
		imageProcessor.activatePipeline(
			imageFilter,
			imageConverter,
			imageExporters
		)

	}

	/**
	 * Calls handle chain for each individual command insertet in parser
	 * @param handler Initial handler of handle chain
	 * @param parser Parser, which holds handled arguments
	 */
	def callArgs(handler: CommandHandler, parser: InputArgumentsParser): Unit = {
		var lastProcessedArgs: List[String] = List.empty
		while (parser.getArgs.nonEmpty && lastProcessedArgs != parser.getArgs) {
			lastProcessedArgs = parser.getArgs
			Handler.handleAll(handler, lastProcessedArgs)
		}
	}

	/**
	 * Creates handle pipeline
	 * @param imageProcessor Image processor
	 * @param filterBuilder Builder for filters
	 * @param converterBuilder Builder for conversion table
	 * @param exporterBuilder Builder for exporters
	 * @param parser Parser, which holds handled arguments
	 * @return Initial command handler of handling chain
	 */
	def handlers(imageProcessor: ImageProcessor,
				 filterBuilder: FilterBuilder,
				 converterBuilder: AsciiConversionBuilder,
				 exporterBuilder: ExporterBuilder,
				 parser: InputArgumentsParser): CommandHandler = {

		// Import handlers
		val importJpgHandler = new ImportJpgHandler(imageProcessor, parser)
		val importPngHandler = new ImportPngHandler(imageProcessor, parser)
		val importRandomHandler = new ImportRandomHandler(imageProcessor, parser)

		// Filter handlers
		val brightnessFilterHandler = new BrightnessFilterHandler(filterBuilder, parser)
		val rotateFilterHandler = new RotateFilterHandler(filterBuilder, parser)
		val flipFilterHandler = new FlipFilterHandler(filterBuilder, parser)
		val invertFilterHandler = new InvertFilterHandler(filterBuilder, parser)

		// Conversion handlers
		val bourkeConverterHandler = new BourkeConverterHandler(converterBuilder, parser)
		val constantConverterHandler = new ConstantConverterHandler(converterBuilder, parser)
		val customConverterHandler = new CustomConverterHandler(converterBuilder, parser)

		// Export handlers
		val stdOutputHandler = new StdOutputHandler(exporterBuilder, parser)
		val fileOutputHandler = new FileOutputHandler(exporterBuilder, parser)

		// Building handle chain
		val initialHandler: CommandHandler = importJpgHandler
		initialHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)
		  .setNext(brightnessFilterHandler)
		  .setNext(rotateFilterHandler)
		  .setNext(flipFilterHandler)
		  .setNext(invertFilterHandler)
		  .setNext(bourkeConverterHandler)
		  .setNext(constantConverterHandler)
		  .setNext(customConverterHandler)
		  .setNext(stdOutputHandler)
		  .setNext(fileOutputHandler)
		initialHandler
	}

}
