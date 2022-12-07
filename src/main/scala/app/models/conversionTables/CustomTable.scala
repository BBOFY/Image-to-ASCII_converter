package app.models.conversionTables

class CustomTable(maxValue: Int) extends LinearTable(maxValue) {
	private var _table: String = "*"
	def setValue(value: String): Unit = {
		if(value.length <= 0) throw new IllegalArgumentException("Conversion table cannot be empty")
		_table = value
	}
}
