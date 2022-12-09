package app

object Commands extends Enumeration {
	type Command = Value

	val image: Commands.Value = Value("--image")
	val imageRandom: Commands.Value  = Value("--image-random")
	val table: Commands.Value  = Value("--table")
	val tableCustom: Commands.Value  = Value("--custom-table")
	val filterRotate: Commands.Value  = Value("--rotate")
	val filterInv: Commands.Value  = Value("--invert")
	val filterFlip: Commands.Value  = Value("--flip")
	val filterBright: Commands.Value  = Value("--brightness")
	val outputConsole: Commands.Value  = Value("--output-console")
	val outputFile: Commands.Value  = Value("--output-file")

	val axisX: Commands.Value = Value("x")
	val axisY: Commands.Value = Value("y")

	val conversionBourke: Commands.Value = Value("bourke")
	val conversionConstant: Commands.Value = Value("constant")

	def isCommand(cmd: String): Boolean = values.exists(_.toString == cmd)
}
