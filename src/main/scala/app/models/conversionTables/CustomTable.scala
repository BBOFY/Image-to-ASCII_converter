package app.models.conversionTables

/**
 * User-defined conversion table using linear conversion
 */
case class CustomTable() extends LinearTable {
	 _table = "*"

	/**
	 * Sets value of conversion table
	 * @param text Text used for conversion
	 * @throws IllegalArgumentException If text is empty string
	 */
	@throws[IllegalArgumentException]
	def setValue(text: String): Unit = {
		if(text.length <= 0) throw new IllegalArgumentException("Conversion table cannot be empty")
		_table = text
	}

	override def equals(obj: Any): Boolean = {
		obj match {
			case that: CustomTable => that._table == this._table
			case _ => false
		}
	}
}
