package app.models.conversionTables

abstract class AsciiTable() extends ConversionTable[Int, Char] {

	protected var _table: String = "*"

	protected val _maxValue: Int = 255
}
