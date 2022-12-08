package app.models.conversionTables

class CustomTableTests extends ConversionTableTests {

	private val table = CustomTable.apply()
	private val customTableContents = "01234567"
	private val invalidTableContents = ""

	test("Valid table initialization") {
		table.setValue(customTableContents)
	}

	validValueTest(table, 0, '0')
	validValueTest(table, 11, '0')
	validValueTest(table, 42, '1')
	validValueTest(table, 79, '2')
	validValueTest(table, 100, '2')
	validValueTest(table, 148, '4')
	validValueTest(table, 193, '5')
	validValueTest(table, 218, '5')
	validValueTest(table, 255, '7')

	invalidValueTest(table, -2)
	invalidValueTest(table, 195318)
	invalidValueTest(table, Int.MaxValue)
	invalidValueTest(table, Int.MinValue)

	test("Invalid table initialization") {
		assertThrows[IllegalArgumentException] {
			table.setValue(invalidTableContents)
		}
	}


}
