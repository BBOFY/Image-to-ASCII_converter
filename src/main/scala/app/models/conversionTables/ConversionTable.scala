package app.models.conversionTables

trait ConversionTable[-C, +P] {
	/**
	 * Converts value into another one
	 * @param inValue Input value to be converted
	 * @return Converted value via conversion table or function
	 */
	def getTableValue(inValue: C): P
}
