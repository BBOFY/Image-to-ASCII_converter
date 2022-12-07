package app.inputParser

import app.Commands

class InputArgumentsParser(private val args: Array[String]) extends InputParser[String]{

	private var _args: List[String] = args.toList

	override def checkValidity: Boolean = {
		var importArgsCount = 0
		var conversionArgsCount = 0
		for (arg <- _args) {
			if (arg == Commands.image || arg == Commands.imageRandom)
				importArgsCount = importArgsCount + 1
			else if (arg == Commands.table || arg == Commands.tableCustom)
				conversionArgsCount = conversionArgsCount + 1
		}
		if (importArgsCount != 1 || conversionArgsCount > 1)
			false
		else true
	}

	override def removeElements(count: Int): List[String] = {
		var newList = _args
		for (c <- 1 to count)
			newList = newList.tail
		newList
	}

	override def getArgs: List[String] = _args

//	sortArguments()
	/*private def sortArguments(): Unit = {
		var argIndex = 0
		while (argIndex < args.length) {
			val arg = args.apply(argIndex)
			try {
				if (arg == cmds.cmdImage) {
					importArgsCount = importArgsCount + 1
					importArgs = importArgs.appended(arg).appended(args.apply(argIndex + 1))
					argIndex = argIndex + 2
				}
				else if (arg == cmds.cmdImageRandom) {
					importArgsCount = importArgsCount + 1
					importArgs = importArgs.appended(arg)
					argIndex = argIndex + 1
				}
				else if (Seq(
					cmds.cmdTable,
					cmds.cmdTableCustom
				).contains(arg)) {
					conversionArgsCount = conversionArgsCount + 1
					convertersArgs = convertersArgs.appended(arg).appended(args.apply(argIndex + 1))
					argIndex = argIndex + 2
				}
				else if (Seq(
					cmds.cmdFilterRotate,
					cmds.cmdFilterScale,
					cmds.cmdFilterFlip,
					cmds.cmdFilterBright
				).contains(arg)) {
					filtersArgs = filtersArgs.appended(arg).appended(args.apply(argIndex + 1))
					argIndex = argIndex + 2
				}
				else if (arg == cmds.cmdFilterInv) {
					filtersArgs = filtersArgs.appended(arg)
					argIndex = argIndex + 1
				}
				else if (arg == cmds.cmdOutputFile) {
					exportArgs = exportArgs.appended(arg).appended(args.apply(argIndex + 1))
					argIndex = argIndex + 2
				}
				else if (arg == cmds.cmdOutputConsole) {
					exportArgs = exportArgs.appended(arg)
					argIndex = argIndex + 1
				}
				else throw new IllegalArgumentException(s"Wrong argument at position \'$argIndex\'")

			} catch {
				case e: ArrayIndexOutOfBoundsException => throw new IllegalArgumentException("Missing argument for last imputed command.")
				case e: IllegalArgumentException => throw e
				case _: Throwable => throw new UnknownError(s"UnknownError at sorting imputed arguments at argument index \'$argIndex")
			}

			if (importArgsCount != 1) throw new IllegalArgumentException("There must be exactly one \"--image*\" argument")
			if (conversionArgsCount > 1) throw new IllegalArgumentException("There must be at most one \"--table\" \"--custom-table\" argument")
		}

	}*/


}
