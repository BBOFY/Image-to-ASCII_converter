package app.models.conversionTables

abstract class LinearTable(maxValue: Int) extends AsciiTable(maxValue) {

	override def getTableValue(numValue: Int): Char = {
		val resultIdx = ((_table.length - 1) * numValue / maxValue.toFloat).floor.toInt
		_table.charAt(resultIdx)
	}
}
