package app.models.conversionTables

abstract class AsciiTable(maxValue: Int) extends ConversionTable[Int, Char] {

	protected val _table: String = ""

	protected val _maxValue: Int = {
		if (maxValue <= 0) throw new IllegalArgumentException("Maximum accepted value must be greater than zero")
		maxValue
	}
}
