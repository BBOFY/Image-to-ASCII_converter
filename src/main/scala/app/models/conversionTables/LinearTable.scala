package app.models.conversionTables

abstract class LinearTable() extends AsciiTable {

	@throws[IllegalArgumentException]
	override def getTableValue(numValue: Int): Char = {
		super.getTableValue(numValue)
		val resultIdx = ((_table.length - 1) * numValue / _maxValue.toFloat).floor.toInt
		_table.charAt(resultIdx)
	}
}
