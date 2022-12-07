package app.models.conversionTables

abstract class NonLinearTable(maxValue: Int) extends AsciiTable(maxValue) {
	override def getTableValue(numValue: Int): Char = {
		val resultIdx = _table.length.toFloat.floor.toInt
		_table.charAt(resultIdx)
	}
}
