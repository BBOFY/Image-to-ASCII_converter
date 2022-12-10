package app.models.conversionTables

abstract class NonLinearTable() extends AsciiTable {

	/**
	 * Converts value into another one using nonlinear function
	 * @param numValue An Int value to be converted into Char value
	 * @throws IllegalArgumentException If numValue is negative or greater than 255
	 * @return Converted value via conversion table or function
	 */
	@throws[IllegalArgumentException]
	override def getTableValue(numValue: Int): Char = {
		super.getTableValue(numValue)
		val resultIdx = _table.length/2.toFloat.floor.toInt
		_table.charAt(resultIdx)
	}
}
