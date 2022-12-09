package app.handlers

import app.builders.ExporterBuilder
import app.handlers.exportHandlers.{ExportHandler, FileOutputHandler, StdOutputHandler}
import app.inputParser.InputArgumentsParser
import exporter.text.TextExporter

class ExportHandlersTests extends HandlerTests {

	class DummyExporterBuilder extends ExporterBuilder {
		var exporters: Seq[TextExporter] = _exporters
		var counter = 0

		override def registerProperty(exporter: TextExporter): Unit = {
			counter = counter + 1
			exporters = exporters.appended(exporter)
		}
	}

	private val args0: Seq[String] = Seq("--output-file", "src/main/resources/test.txt")
	private val args1: Seq[String] = Seq("--output-console", "whatever", "--output-console")
	private val args2: Seq[String] = Seq("--output-file", "src/main/resources/test.txt", "--output-console")
	private val args3: Seq[String] = Seq("--output-console", "--output-file", "src/main/resources/test.txt", "whatever", "src/main/resources/test.jpg", "--output-console")

	private val builder = new DummyExporterBuilder


	test("Simple input") {
		builder.counter = 0
		builder.exporters = Seq.empty
		val parser = new InputArgumentsParser(args0)
		val handlers = exportHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 1)
		assert(builder.exporters.length == 1)
	}

	test("Simple input with invalid command") {
		builder.counter = 0
		builder.exporters = Seq.empty
		val parser = new InputArgumentsParser(args1)
		val handlers = exportHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("whatever", "--output-console"))
		assert(builder.counter == 1)
		assert(builder.exporters.length == 1)
	}

	test("Valid input with multiple outputs") {
		builder.counter = 0
		builder.exporters = Seq.empty
		val parser = new InputArgumentsParser(args2)
		val handlers = exportHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 2)
		assert(builder.exporters.length == 2)
	}

	test("Input partially correct") {
		builder.counter = 0
		builder.exporters = Seq.empty
		val parser = new InputArgumentsParser(args3)
		val handlers = exportHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("whatever", "src/main/resources/test.jpg", "--output-console"))
		assert(builder.counter == 2)
		assert(builder.exporters.length == 2)
	}

	private def exportHandlers(parser: InputArgumentsParser): ExportHandler = {

		val exportConsoleHandler = new StdOutputHandler(builder, parser)
		val exportFileHandler = new FileOutputHandler(builder, parser)

		val initialImportHandler: ExportHandler = exportConsoleHandler
		initialImportHandler
		  .setNext(exportFileHandler)

		initialImportHandler
	}
}
