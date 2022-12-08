package app.builders

import app.converters.AsciiConverter
import app.models.conversionTables.AsciiTable

class AsciiConversionBuilder extends Builder[AsciiTable, AsciiConverter] {
	private var converter = new AsciiConverter

	override def registerProperty(table: AsciiTable): Unit = converter = new AsciiConverter(table)

	override def build: AsciiConverter = converter
}
