package app.models.conversionTables


/**
 * Conversion table using nonlinear (in this case constant) conversion.
 * Conversion function takes roughly middle character from _table string as output value
 */
case class ConstantTable() extends NonLinearTable {
	_table = "qwerty0uiopa ,./;'\\]9[sdfghjkl1z2x3c4v5b6n7m8"

	override def equals(obj: Any): Boolean = {
		obj match {
			case that: ConstantTable => that._table == this._table
			case _ => false
		}
	}
}
