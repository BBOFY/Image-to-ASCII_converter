package app.models.conversionTables

abstract class NonLinearTable() extends AsciiTable {
	override def getTableValue(numValue: Int): Char = {
		val resultIdx = _table.length.toFloat.floor.toInt
		_table.charAt(resultIdx)
	}
}
