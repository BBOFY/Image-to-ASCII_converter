package app.converters

class AsciiUserDefinedConverter(val conversionTable: String) extends AsciiLinearConverter {

	override protected def findAsciiValue(numValue: Int): Char = {
		val resultIdx = ((customTable.length - 1) * numValue / 255).floor.toInt
		customTable.charAt(resultIdx)
	}
}
