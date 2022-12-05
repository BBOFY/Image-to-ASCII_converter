package app.inputParser

import app.models.commands.Commands

class InputArgumentsParser(private val args: Array[String], val cmds: Commands ) extends InputParser[List[String]]{

	private var importArgs: List[String] = List.empty
	private var filtersArgs: List[String] = List.empty
	private var convertersArgs: List[String] = List.empty
	private var exportArgs: List[String] = List.empty

	private var importArgsCount = 0
	private var conversionArgsCount = 0

	sortArguments()

	override def getImageSource: List[String] = importArgs

	override def getFilterArgs: List[String] = filtersArgs

	override def getConversionArgs: List[String] = convertersArgs

	override def getExportingArgs: List[String] = exportArgs
	
	private def sortArguments(): Unit = {
		var argIndex = 0
		while (argIndex < args.length) {
			val arg = args.apply(argIndex)
			try {
				arg match {
					case cmds.cmdImage =>
						importArgsCount = importArgsCount + 1
						importArgs = importArgs.appended(cmds.cmdImage).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdImageRandom =>
						importArgsCount = importArgsCount + 1
						importArgs = importArgs.appended(cmds.cmdImageRandom)
						argIndex = argIndex + 1

					case cmd == cmds.cmdTable | cmds.cmdTableCustom =>
						conversionArgsCount = conversionArgsCount + 1
						convertersArgs = convertersArgs.appended(cmd).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdFilterRotate =>
						filtersArgs = filtersArgs.appended(cmds.cmdFilterRotate).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdFilterScale =>
						filtersArgs = filtersArgs.appended(cmds.cmdFilterScale).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdFilterInv =>
						filtersArgs = filtersArgs.appended(cmds.cmdFilterInv)
						argIndex = argIndex + 1

					case cmds.cmdFilterFlip =>
						filtersArgs = filtersArgs.appended(cmds.cmdFilterFlip).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdFilterBright =>
						filtersArgs = filtersArgs.appended(cmds.cmdFilterBright).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdOutputFile =>
						exportArgs = exportArgs.appended(cmds.cmdOutputFile).appended(args.apply(argIndex + 1))
						argIndex = argIndex + 2

					case cmds.cmdOutputConsole =>
						exportArgs = exportArgs.appended(cmds.cmdOutputConsole)
						argIndex = argIndex + 1

					case _ => throw new IllegalArgumentException(s"Wrong argument at position \'$argIndex\'")
				}
			} catch {
				case e: ArrayIndexOutOfBoundsException => throw new IllegalArgumentException("Missing argument for last imputed command.")
				case e: IllegalArgumentException => throw e
				case e: _ => throw new UnknownError(s"UnknownError at sorting imputed arguments at argument index \'$argIndex")
			}

			if (importArgsCount != 1) throw new IllegalArgumentException("There must be exactly one \"--image*\" argument")
			if (conversionArgsCount > 1) throw new IllegalArgumentException("There must be at most one \"--table\" \"--custom-table\" argument")
		}

	}

}
