package app.handlers

import app.enums.{Axes, Commands, Tables}
import org.scalatest.FunSuite

import scala.language.postfixOps
import scala.util.Random

class ArgumentsHandlingIntegrationTests extends FunSuite {








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
		val testFiles: Seq[String] = Seq("test.jpg", "test.png")
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
			val randomRotation = 90 + 90 * Random.between(Int.MinValue, Int.MaxValue)
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
			val randomStringLen = Random.between(1, 100)
			val randomString = Random.alphanumeric take randomStringLen mkString

			val commands = Seq(
				Seq(Commands.outputConsole.toString),
				Seq(Commands.outputFile.toString, randomString)
			)

			val idx = Random.between(0, commands.length)
			returnSeq = returnSeq.appended(commands.apply(idx))
		}
		returnSeq
	}
}
