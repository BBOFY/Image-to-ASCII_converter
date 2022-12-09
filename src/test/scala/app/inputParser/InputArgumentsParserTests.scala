package app.inputParser

import app.enums.{Axes, Commands, Tables}
import org.scalatest.FunSuite

class InputArgumentsParserTests extends FunSuite {

	protected val cmds: Commands.type = Commands
	protected val validInputs: Seq[Seq[String]] = Seq(
		Seq(cmds.image.toString, "path", cmds.filterFlip.toString, Axes.x.toString, cmds.filterInv.toString, cmds.outputConsole.toString),
		Seq(cmds.imageRandom.toString, cmds.filterFlip.toString, Axes.x.toString, cmds.filterInv.toString, cmds.outputConsole.toString),
		Seq(cmds.image.toString, "path"),
		Seq(cmds.outputFile.toString, "path", cmds.image.toString, "path", cmds.filterFlip.toString, Axes.x.toString, cmds.filterInv.toString, cmds.outputConsole.toString)
	)

	for (i <- validInputs.indices) {
		test(s"Validity on valid args $i") {
			val parser: InputParser[String] = new InputArgumentsParser(validInputs.apply(i))
			parser.checkValidity()

			parser.removeElements(2)
			assert(
				parser.getArgs == validInputs.apply(i).tail.tail
			)
		}
	}

	protected val invalidInputs: Seq[Seq[String]] = Seq(
		Seq(cmds.image.toString, "path", cmds.filterFlip.toString, Axes.x.toString, cmds.imageRandom.toString, cmds.outputConsole.toString),
		Seq(cmds.image.toString, "path", cmds.table.toString, Tables.conversionBourke.toString, cmds.filterFlip.toString, Axes.x.toString, cmds.imageRandom.toString, cmds.table.toString, Tables.conversionConstant.toString, cmds.outputConsole.toString),
		Seq(cmds.table.toString, Tables.conversionBourke.toString),
		Seq(),
		Seq(cmds.filterFlip.toString, Axes.x.toString, cmds.tableCustom.toString, "table", cmds.imageRandom.toString, cmds.table.toString, Tables.conversionBourke.toString),
	)

	for (i <- invalidInputs.indices) {
		test(s"Validity on invalid args $i") {
			val parser: InputParser[String] = new InputArgumentsParser(invalidInputs.apply(i))
			assertThrows[IllegalArgumentException] {
				parser.checkValidity()
			}

		}
	}

	test("Correct args") {
		val orgArgs: Seq[String] = Seq(cmds.image.toString, "path", cmds.filterFlip.toString, Axes.x.toString, cmds.filterInv.toString, cmds.outputConsole.toString)
		val refArgsRemove1: Seq[String] = Seq(cmds.filterFlip.toString, Axes.x.toString, cmds.filterInv.toString, cmds.outputConsole.toString)
		val refArgsRemove2: Seq[String] = Seq(cmds.filterInv.toString, cmds.outputConsole.toString)
		val refArgsRemove3: Seq[String] = Seq(cmds.outputConsole.toString)
		val refArgsRemove4: Seq[String] = Seq()

		val parser = new InputArgumentsParser(orgArgs)
		assert(orgArgs.toList == parser.getArgs)

		assert(
			!parser.argsEmpty()
		)

		parser.checkValidity()

		assertThrows[IllegalArgumentException] {
			parser.removeElements(-21)
		}

		parser.removeElements(0)
		assert(orgArgs.toList == parser.getArgs)

		parser.removeElements(2)
		assert(refArgsRemove1.toList == parser.getArgs)

		parser.removeElements(2)
		assert(refArgsRemove2.toList == parser.getArgs)

		parser.removeElements(0)
		assert(refArgsRemove2.toList == parser.getArgs)

		parser.removeElements(1)
		assert(refArgsRemove3.toList == parser.getArgs)

		parser.removeElements(1)
		assert(refArgsRemove4.toList == parser.getArgs)

		assertThrows[IllegalArgumentException] {
			parser.removeElements(1)
		}

		assert(
			parser.argsEmpty()
		)

	}

	test("Emptiness valid") {
		val emptyArgs = Seq.empty
		val parser = new InputArgumentsParser(emptyArgs)
		assert(
			parser.argsEmpty()
		)
	}

	test("Emptiness invalid") {
		val invalidArgs = Seq(".empty", "den", "")
		val parser = new InputArgumentsParser(invalidArgs)

		assert(
			!parser.argsEmpty()
		)
	}

}
