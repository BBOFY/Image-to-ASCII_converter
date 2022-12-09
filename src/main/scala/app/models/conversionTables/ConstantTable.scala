package app.models.conversionTables


case class ConstantTable() extends NonLinearTable {
	_table = "qwerty0uiopa ,./;'\\]9[sdfghjkl1z2x3c4v5b6n7m8"

	override def equals(obj: Any): Boolean = {
		obj match {
			case that: ConstantTable => that._table == this._table
			case _ => false
		}
	}
}
