package app

object Commands extends Enumeration {
	type Command = Value

	val image = "--image"
	val imageRandom  = "--image-random"
	val table  = "--table"
	val tableCustom  = "--custom-table"
	val filterRotate  = "--rotate"
	val filterInv  = "--invert"
	val filterFlip  = "--flip"
	val filterBright  = "--brightness"
	val outputConsole  = "--output-console"
	val outputFile  = "--output-file"

	val axisX = "x"
	val axisY = "y"

	val conversionBourke = "bourke"
	val conversionConstant = "constant"
}
