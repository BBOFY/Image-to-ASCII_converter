package app.models.conversionTables

trait ConversionTable[-C, +P] {
	def getTableValue(inValue: C): P
}
