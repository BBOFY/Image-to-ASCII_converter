package app.inputParser

import app.enums.Commands

class InputArgumentsParser(private val args: Seq[String]) extends InputParser[String]{

	private var _args: List[String] = args.toList

	/**
	 * Checks validity of parsed input. If input is invalid, an exception is thrown, otherwise nothing happens
	 * @throws IllegalArgumentException if in input isn't exactly one image-type argument or there is more than one table-type argument
	 */
	@throws[IllegalArgumentException]
	override def checkValidity(): Unit = {
		var importArgsCount = 0
		var conversionArgsCount = 0
		for (arg <- _args) {
			if (arg == Commands.image.toString || arg == Commands.imageRandom.toString)
				importArgsCount = importArgsCount + 1
			else if (arg == Commands.table.toString || arg == Commands.tableCustom.toString)
				conversionArgsCount = conversionArgsCount + 1
		}
		if (importArgsCount != 1)
			throw new IllegalArgumentException("There must be exactly one \'--image*\' command\n")
		if (conversionArgsCount > 1)
			throw new IllegalArgumentException("There must be at most one \'--table\' and \'--custom-table\' command\n")

	}

	/**
	 * Remove desired number of arguments from parsing input
	 * @param count Number of arguments to remove
	 */
	override def removeElements(count: Int): Unit = {
		if (count > _args.length || count < 0)
			throw new IllegalArgumentException("There are not enough elements to remove")
		for (c <- 1 to count)
			_args = _args.tail
	}

	/**
	 * Gets list of arguments in current parsing state
	 * @return List of currently parsing arguments
	 */
	override def getArgs: List[String] = _args

	/**
	 * @return True, if parsed input is empty, otherwise false
	 */
	override def argsEmpty(): Boolean = {
		_args.isEmpty

	}
}
