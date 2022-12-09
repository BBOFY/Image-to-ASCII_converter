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
	private val args1: Seq[String] = Seq("--output-console", "whatever", "--image-random")
	private val args2: Seq[String] = Seq("--output-file", "src/main/resources/test.gif", "--output-console")
	private val args3: Seq[String] = Seq("--output-console", "--output-file", "src/main/resources/test.txt", "whatever", "src/main/resources/test.jpg", "--output-console")

	private val builder = new DummyExporterBuilder


	private def exportHandlers(parser: InputArgumentsParser): ExportHandler = {

		val exportConsole = new StdOutputHandler(builder, parser)
		val exportFile = new FileOutputHandler(builder, parser)

		val initialImportHandler: ExportHandler = exportConsole
		initialImportHandler
		  .setNext(exportFile)

		initialImportHandler
	}
}
