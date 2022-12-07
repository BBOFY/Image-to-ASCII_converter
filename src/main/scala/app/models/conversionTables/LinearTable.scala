package app.models.conversionTables

abstract class LinearTable(maxValue: Int) extends AsciiTable(maxValue) {

	override def getTableValue(numValue: Int): Char = {
		val resultIdx = ((table.length - 1) * numValue / maxValue).floor.toInt
		table.charAt(resultIdx)
	}
}
