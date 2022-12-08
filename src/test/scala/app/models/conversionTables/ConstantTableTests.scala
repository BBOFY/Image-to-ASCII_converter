package app.models.conversionTables

import org.scalatest.FunSuite

class ConstantTableTests extends ConversionTableTests {

	private val table = ConstantTable.apply()

	validValueTest(table, 0, 's')
	validValueTest(table, 11, 's')
	validValueTest(table, 42, 's')
	validValueTest(table, 79, 's')
	validValueTest(table, 100, 's')
	validValueTest(table, 148, 's')
	validValueTest(table, 193, 's')
	validValueTest(table, 218, 's')
	validValueTest(table, 255, 's')

	invalidValueTest(table, -2)
	invalidValueTest(table, 195318)
	invalidValueTest(table, Int.MaxValue)
	invalidValueTest(table, Int.MinValue)

}
