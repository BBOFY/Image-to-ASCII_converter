package app.builders

import app.converters.AsciiConverter
import app.models.conversionTables.AsciiTable

class AsciiConversionBuilder extends Builder[AsciiTable, AsciiConverter] {
	private var converter = new AsciiConverter

	/**
	 *  Set new conversion method using given table
	 *  @param table Conversion table to use for built converter
	 */
	override def registerProperty(table: AsciiTable): Unit = converter = new AsciiConverter(table)

	/**
	 *  @return Ascii converter using conversion table registered previously, or using Bourke table as default if nothing was registered
	 */
	override def build: AsciiConverter = converter
}
