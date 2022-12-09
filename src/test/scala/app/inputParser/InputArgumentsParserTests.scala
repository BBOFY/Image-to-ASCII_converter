package app.inputParser

import app.Commands
import org.scalatest.FunSuite

class InputArgumentsParserTests extends FunSuite {

	protected val cmds: Commands.type = Commands
	protected val validInputs: Seq[Array[String]] = Seq(
		Array(cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole),
		Array(cmds.imageRandom, cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole),
		Array(cmds.image, "path"),
		Array(cmds.outputFile, "path", cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole)
	)

	for (i <- validInputs.indices) {
		test(s"Validity on valid args $i") {
			val parser: InputParser[String] = new InputArgumentsParser(validInputs.apply(i))
			parser.checkValidity()

			parser.removeElements(2)
			assert(
				parser.getArgs == validInputs.apply(i).tail.tail.toSeq
			)
		}
	}

	protected val invalidInputs: Seq[Array[String]] = Seq(
		Array(cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.imageRandom, cmds.outputConsole),
		Array(cmds.image, "path", cmds.table, cmds.conversionBourke, cmds.filterFlip, cmds.axisX, cmds.imageRandom, cmds.table, cmds.conversionConstant, cmds.outputConsole),
		Array(cmds.table, cmds.conversionBourke),
		Array(),
		Array(cmds.filterFlip, cmds.axisX, cmds.tableCustom, "table", cmds.imageRandom, cmds.table, cmds.conversionBourke),
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
		val orgArgs: Array[String] = Array(cmds.image, "path", cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole)
		val refArgsRemove1: Array[String] = Array(cmds.filterFlip, cmds.axisX, cmds.filterInv, cmds.outputConsole)
		val refArgsRemove2: Array[String] = Array(cmds.filterInv, cmds.outputConsole)
		val refArgsRemove3: Array[String] = Array(cmds.outputConsole)
		val refArgsRemove4: Array[String] = Array()

		val parser = new InputArgumentsParser(orgArgs)
		assert(orgArgs.toList == parser.getArgs)

		assertThrows[IllegalStateException] {
			parser.argsEmptiness()
		}

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

		parser.argsEmptiness()

	}

	test("Emptiness valid") {
		val emptyArgs = Array.empty
		val parser = new InputArgumentsParser(emptyArgs)
		parser.argsEmptiness()
	}

	test("Emptiness invalid") {
		val invalidArgs = Array(".empty", "den", "")
		val parser = new InputArgumentsParser(invalidArgs)

		val caught = intercept[IllegalStateException] {
			parser.argsEmptiness()
		}

		assertResult("Unknown command '.empty'") {
			caught.getMessage
		}
	}

}
