package app.handlers

import app.builders.AsciiConversionBuilder
import app.handlers.converterHandlers.{PredefinedConverterHandler, ConverterHandler, CustomConverterHandler}
import app.inputParser.InputArgumentsParser
import app.models.conversionTables.{AsciiTable, BourkeTable, ConstantTable, CustomTable}

class ConverterHandlersTests extends HandlerTests {

	class DummyConverterBuilder extends AsciiConversionBuilder {
		var table: AsciiTable = BourkeTable.apply()
		var counter = 0
		override def registerProperty(item: AsciiTable): Unit = {
			counter = counter + 1
			table = item
		}
	}

	private val builder = new DummyConverterBuilder

	private val args0: Seq[String] = Seq("--table", "constant")
	private val args1: Seq[String] = Seq("--table", "bourke", "--custom-table", "abcd")
	private val args2: Seq[String] = Seq("--table", "bourke", "--custom-table")
	private val args3: Seq[String] = Seq("--custom-table", "whatever", "command")

	test("Small valid input") {
		builder.counter = 0
		val parser = new InputArgumentsParser(args0)
		val handlers = conversionHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 1)
		assert(builder.table == ConstantTable.apply())
	}

	test("Valid input") {
		builder.counter = 0
		val parser = new InputArgumentsParser(args1)
		val handlers = conversionHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(parser.argsEmpty())
		assert(parser.getArgs == Seq.empty)
		assert(builder.counter == 2)
		val table = CustomTable.apply()
		table.setValue("abcd")
		assert(builder.table == table)
	}

	test("Last command not finished") {
		builder.counter = 0
		val parser = new InputArgumentsParser(args2)
		val handlers = conversionHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("--custom-table"))
		assert(builder.counter == 1)
		assert(builder.table == BourkeTable.apply())
	}

	test("With invalid command") {
		builder.counter = 0
		val parser = new InputArgumentsParser(args3)
		val handlers = conversionHandlers(parser)

		assert(!parser.argsEmpty())
		callArgs(handlers, parser)
		assert(!parser.argsEmpty())
		assert(parser.getArgs == Seq("command"))
		assert(builder.counter == 1)
		val table = CustomTable.apply()
		table.setValue("whatever")
		assert(builder.table == table)
	}


	private def conversionHandlers(parser: InputArgumentsParser): ConverterHandler = {

		val bourkeConverterHandler = new PredefinedConverterHandler(builder, parser)
		val constantConverterHandler = new PredefinedConverterHandler(builder, parser)
		val customConverterHandler = new CustomConverterHandler(builder, parser)

		val initialImportHandler: ConverterHandler = bourkeConverterHandler
		initialImportHandler
		  .setNext(constantConverterHandler)
		  .setNext(customConverterHandler)

		initialImportHandler
	}

}
