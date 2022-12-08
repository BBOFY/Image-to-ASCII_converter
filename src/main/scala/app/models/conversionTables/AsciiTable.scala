package app.models.conversionTables

abstract class AsciiTable extends ConversionTable[Int, Char] {

	protected var _table: String = "*"

	protected val _maxValue: Int = 255

	@throws[IllegalArgumentException]
	override def getTableValue(numValue: Int): Char = {
		if (numValue < 0 || numValue > _maxValue)
			throw new IllegalArgumentException(s"Number value must be non-negative and cannot be greater than ${_maxValue}")
		Char.MinValue
	}
}
