package app.models.conversionTables

class CustomTable(maxValue: Int, table: String) extends LinearTable(maxValue) {
	override val _table: String = {
		if (table.length <= 0) throw new IllegalArgumentException("Table must have at least one character")
		table
	}
}
