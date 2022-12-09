package app.builders

import exporter.text.{FileOutputExporter, StdOutputExporter, TextExporter}
import org.scalatest.FunSuite

class ExporterBuilderTests extends FunSuite {

	protected val path = "src/main/resources/test.txt"

	protected val stdOutputExporter = new StdOutputExporter
	protected val fileOutputExporter = new FileOutputExporter(path)

	protected val noExporters: Seq[TextExporter] = Seq.empty
	protected val stdOutExporter: Seq[TextExporter] = Seq(stdOutputExporter)
	protected val fileExporter: Seq[TextExporter] = Seq(fileOutputExporter)
	protected val stdOutAndFileExporter: Seq[TextExporter] = Seq(stdOutputExporter, fileOutputExporter)
	protected val FileAndStdOutExporter: Seq[TextExporter] = Seq(fileOutputExporter, stdOutputExporter)



	test("Default exporter") {
		val builder = new ExporterBuilder
		assert(builder.build == noExporters)
	}

	test("StdOut exporter") {
		val builder = new ExporterBuilder
		builder.registerProperty(new StdOutputExporter)
		assert(builder.build == stdOutExporter)
	}
	test("File exporter") {
		val builder = new ExporterBuilder
		builder.registerProperty(new FileOutputExporter(path))
		assert(builder.build == fileExporter)
	}

	test("StdOut and file exporter") {
		val builder = new ExporterBuilder
		builder.registerProperty(new StdOutputExporter)
		builder.registerProperty(new FileOutputExporter(path))
		assert(builder.build == stdOutAndFileExporter)
	}

	test("File and stdOut exporter") {
		val builder = new ExporterBuilder
		builder.registerProperty(new FileOutputExporter(path))
		builder.registerProperty(new StdOutputExporter)
		assert(builder.build == FileAndStdOutExporter)
	}


}
