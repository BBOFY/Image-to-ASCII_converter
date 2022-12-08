package app.models.conversionTables

case class CustomTable() extends LinearTable {
	 _table = "*"

	@throws[IllegalArgumentException]
	def setValue(value: String): Unit = {
		if(value.length <= 0) throw new IllegalArgumentException("Conversion table cannot be empty")
		_table = value
	}
}
