package app

import app.builders.{AsciiConversionBuilder, ExporterBuilder, FilterBuilder}
import app.enums.{Axes, Commands, Tables}
import app.handlers.CommandHandler
import app.handlers.converterHandlers.{PredefinedConverterHandler, CustomConverterHandler}
import app.handlers.exportHandlers.{FileOutputHandler, StdOutputHandler}
import app.handlers.filterHandlers.{BrightnessFilterHandler, FlipFilterHandler, InvertFilterHandler, RotateFilterHandler}
import app.handlers.importHandlers.{ImportJpgHandler, ImportPngHandler, ImportRandomHandler}
import app.inputParser.InputArgumentsParser
import app.processor.{ImageProcessor, ImageProcessorImpl}
import handler.Handler
import org.scalatest.FunSuite

import scala.language.postfixOps
import scala.util.Random

class ArgumentsHandlingIntegrationTests extends FunSuite {

	private val processor = new ImageProcessorImpl
	private val filterBuilder = new FilterBuilder
	private val conversionBuilder = new AsciiConversionBuilder
	private val exporterBuilder = new ExporterBuilder


	// Just for visualization of test progress
	private val nOfTests = Random.between(1, 5000)
	println(s"[info] - Running $nOfTests valid random args tests:")
	private val step = (nOfTests / 50.0).ceil.toInt


	for (i <- 1 to nOfTests) {
		if (i % step == 0)
			print(".")
		val args = validArgsGenerator
		val parser = new InputArgumentsParser(args)
		val handler = handlers(
			processor,
			filterBuilder,
			conversionBuilder,
			exporterBuilder,
			parser
		)
		callArgs(handler, parser)

		test(s"Valid random args $i") {
			assert(
				parser.argsEmpty()
			)
		}
	}
	println(".")

	private def callArgs(handler: CommandHandler, parser: InputArgumentsParser): Unit = {
		var lastProcessedArgs: List[String] = List.empty
		while (parser.getArgs.nonEmpty && lastProcessedArgs != parser.getArgs) {
			lastProcessedArgs = parser.getArgs
			Handler.handleAll(handler, lastProcessedArgs)
		}
	}


	private def validArgsGenerator: Seq[String] = {
		val inputArgs = inputArgsGenerator
		val filterArgs = filterArgsGenerator
		val convertArgs = converterArgsGenerator
		val exportArgs = exporterArgsGenerator

		var sequenceOfArgs = inputArgs ++ filterArgs ++ convertArgs ++ exportArgs
		sequenceOfArgs = Random.shuffle(sequenceOfArgs)

		val args = sequenceOfArgs.flatten
		args
	}

	private def inputArgsGenerator: Seq[Seq[String]] = {

		var returnSeq: Seq[Seq[String]] = Seq.empty

		val testFiles: Seq[String] = Seq("src/main/resources/test.jpg", "src/main/resources/test.png")
		val fileNameIdx = Random.between(0, testFiles.length)

		val commands = Seq(
			Seq(Commands.image.toString, testFiles.apply(fileNameIdx)),
			Seq(Commands.imageRandom.toString)
		)

		val idx = Random.between(0, commands.length)
		returnSeq = returnSeq.appended(commands.apply(idx))

		returnSeq
	}

	private def filterArgsGenerator: Seq[Seq[String]] = {
		var returnSeq: Seq[Seq[String]] = Seq.empty

		for (i <- 1 to Random.nextInt(16)) {
			val randomRotation = 90 + 90 * Random.between(-200, 200)
			val randomBrightness = Random.between(Int.MinValue, Int.MaxValue)

			val commands = Seq(
				Seq(Commands.filterRotate.toString, randomRotation.toString),
				Seq(Commands.filterBright.toString, randomBrightness.toString),
				Seq(Commands.filterFlip.toString, Axes.x.toString),
				Seq(Commands.filterFlip.toString, Axes.y.toString),
				Seq(Commands.filterInv.toString)
			)

			val idx = Random.between(0, commands.length)
			returnSeq = returnSeq.appended(commands.apply(idx))
		}
		returnSeq
	}

	private def converterArgsGenerator: Seq[Seq[String]] = {
		var returnSeq: Seq[Seq[String]] = Seq.empty

		for (i <- 0 until  Random.between(0, 1)) {
			val randomStringLen = Random.between(1, 100)
			val randomString = Random.alphanumeric take randomStringLen mkString

			val commands = Seq(
				Seq(Commands.table.toString, Tables.conversionBourke.toString),
				Seq(Commands.table.toString, Tables.conversionConstant.toString),
				Seq(Commands.tableCustom.toString, randomString),
			)

			val idx = Random.between(0, commands.length)
			returnSeq = returnSeq.appended(commands.apply(idx))
		}
		returnSeq
	}

	private def exporterArgsGenerator: Seq[Seq[String]] = {
		var returnSeq: Seq[Seq[String]] = Seq.empty

		for (i <- 1 to Random.nextInt(16)) {
			val commands = Seq(
				Seq(Commands.outputConsole.toString),
				Seq(Commands.outputFile.toString, "src/main/resources/dummy.txt")
			)

			val idx = Random.between(0, commands.length)
			returnSeq = returnSeq.appended(commands.apply(idx))
		}
		returnSeq
	}

	private def handlers(imageProcessor: ImageProcessor,
				 filterBuilder: FilterBuilder,
				 converterBuilder: AsciiConversionBuilder,
				 exporterBuilder: ExporterBuilder,
				 parser: InputArgumentsParser): CommandHandler = {

		val importJpgHandler = new ImportJpgHandler(imageProcessor, parser)
		val importPngHandler = new ImportPngHandler(imageProcessor, parser)
		val importRandomHandler = new ImportRandomHandler(imageProcessor, parser)

		val brightnessFilterHandler = new BrightnessFilterHandler(filterBuilder, parser)
		val rotateFilterHandler = new RotateFilterHandler(filterBuilder, parser)
		val flipFilterHandler = new FlipFilterHandler(filterBuilder, parser)
		val invertFilterHandler = new InvertFilterHandler(filterBuilder, parser)

		val predefinedConverterHandler = new PredefinedConverterHandler(converterBuilder, parser)
		val customConverterHandler = new CustomConverterHandler(converterBuilder, parser)

		val stdOutputHandler = new StdOutputHandler(exporterBuilder, parser)
		val fileOutputHandler = new FileOutputHandler(exporterBuilder, parser)

		val initialHandler: CommandHandler = importJpgHandler

		initialHandler
		  .setNext(importPngHandler)
		  .setNext(importRandomHandler)
		  .setNext(brightnessFilterHandler)
		  .setNext(rotateFilterHandler)
		  .setNext(flipFilterHandler)
		  .setNext(invertFilterHandler)
		  .setNext(predefinedConverterHandler)
		  .setNext(customConverterHandler)
		  .setNext(stdOutputHandler)
		  .setNext(fileOutputHandler)

		initialHandler
	}
}
