package app.inputParser

import app.Commands

class InputArgumentsParser(private val args: Array[String]) extends InputParser[String]{

	private var _args: List[String] = args.toList

	override def checkValidity(): Unit = {
		var importArgsCount = 0
		var conversionArgsCount = 0
		for (arg <- _args) {
			if (arg == Commands.image || arg == Commands.imageRandom)
				importArgsCount = importArgsCount + 1
			else if (arg == Commands.table || arg == Commands.tableCustom)
				conversionArgsCount = conversionArgsCount + 1
		}
		if (importArgsCount != 1)
			throw new IllegalArgumentException("There must be exactly one \'--iamge*\' command")
		if (conversionArgsCount > 1)
			throw new IllegalArgumentException("There must be at most one \'--table\' and \'--custom-table\' command")

	}

	override def removeElements(count: Int): List[String] = {
		for (c <- 1 to count)
			_args = _args.tail
		_args
	}

	override def getArgs: List[String] = _args

	override def argsStatus(): String = {
		if (_args.nonEmpty)
			s"Unknown command '${_args.head}'\n"
		else
			"Commands processed\n"
	}
}
