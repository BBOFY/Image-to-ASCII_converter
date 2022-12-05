//package app.converters
//
//class AsciiUserDefinedConverter(val conversionTable: String) extends AsciiLinearConverter {
//
//	override protected def findAsciiValue(numValue: Int): Char = {
//		val resultIdx = ((conversionTable.length - 1) * numValue / 255).floor.toInt
//		conversionTable.charAt(resultIdx)
//	}
//}
