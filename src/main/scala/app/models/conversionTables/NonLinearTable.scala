package app.models.conversionTables

abstract class NonLinearTable() extends AsciiTable {

	@throws[IllegalArgumentException]
	override def getTableValue(numValue: Int): Char = {
		super.getTableValue(numValue)
		val resultIdx = _table.length.toFloat.floor.toInt
		_table.charAt(resultIdx)
	}
}
