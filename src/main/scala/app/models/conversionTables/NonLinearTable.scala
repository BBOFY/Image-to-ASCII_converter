package app.models.conversionTables

abstract class NonLinearTable(maxValue: Int) extends AsciiTable(maxValue) {
	override def getTableValue(numValue: Int): Char = {
		val resultIdx = 0
		table.charAt(resultIdx)
	}
}
