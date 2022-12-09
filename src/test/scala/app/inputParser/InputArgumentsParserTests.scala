package app.inputParser

import app.Commands
import org.scalatest.FunSuite

class InputArgumentsParserTests extends FunSuite {

	protected val cmds: Commands.type = Commands
	protected val validInputs: Seq[Seq[String]] = Seq(
		Seq(cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole),
		Seq(cmds.imageRandom, cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole),
		Seq(cmds.image, "path"),
		Seq(cmds.outputFile, "path", cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole)
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
		Seq(cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.imageRandom, cmds.outputConsole),
		Seq(cmds.image, "path", cmds.table, cmds.conversionBourke, cmds.filterFlip, cmds.axisX, cmds.imageRandom, cmds.table, cmds.conversionConstant, cmds.outputConsole),
		Seq(cmds.table, cmds.conversionBourke),
		Seq(),
		Seq(cmds.filterFlip, cmds.axisX, cmds.tableCustom, "table", cmds.imageRandom, cmds.table, cmds.conversionBourke),
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
		val orgArgs: Seq[String] = Seq(cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole)
		val refArgsRemove1: Seq[String] = Seq(cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole)
		val refArgsRemove2: Seq[String] = Seq(cmds.filterInv, cmds.outputConsole)
		val refArgsRemove3: Seq[String] = Seq(cmds.outputConsole)
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
