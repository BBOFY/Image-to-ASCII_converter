package app.models.conversionTables


abstract class AsciiTable extends ConversionTable[Int, Char] {

	protected var _table: String = "*"

	protected val _maxValue: Int = 255

	/**
	 * Converts value into another one
	 * @param numValue An Int value to be converted into Char value
	 * @throws IllegalArgumentException If numValue is negative or greater than 255
	 * @return Converted value via conversion table or function
	 */
	@throws[IllegalArgumentException]
	override def getTableValue(numValue: Int): Char = {
		if (numValue < 0 || numValue > _maxValue)
			throw new IllegalArgumentException(s"Number value must be non-negative and cannot be greater than ${_maxValue}")
		Char.MinValue
	}
}
