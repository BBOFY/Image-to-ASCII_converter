package app.models.conversionTables

abstract class AsciiTable(maxValue: Int) extends ConversionTable[Int, Char] {

	protected val table: String = ""

	val _maxValue = {
		if (maxValue <= 0) throw new IllegalArgumentException("Maximum accepted value must be greater than zero")
		maxValue
	}
}
