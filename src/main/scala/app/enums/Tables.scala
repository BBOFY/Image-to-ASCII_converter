package app.enums

object Tables extends Enumeration {

	type Table = Value

	val conversionBourke: Tables.Value = Value("bourke")
	val conversionConstant: Tables.Value = Value("constant")

	def isTable(table: String): Boolean = values.exists(_.toString == table)

}
