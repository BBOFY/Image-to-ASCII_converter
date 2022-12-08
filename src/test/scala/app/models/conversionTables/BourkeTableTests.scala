package app.models.conversionTables

class BourkeTableTests extends ConversionTableTests {
	private val table = BourkeTable.apply()

	validValueTest(table, 0, '$')
	validValueTest(table, 11, 'B')
	validValueTest(table, 42, 'a')
	validValueTest(table, 79, 'O')
	validValueTest(table, 100, 'U')
	validValueTest(table, 148, '/')
	validValueTest(table, 193, '_')
	validValueTest(table, 218, '!')
	validValueTest(table, 255, ' ')

	invalidValueTest(table, -2)
	invalidValueTest(table, 195318)
	invalidValueTest(table, Int.MaxValue)
	invalidValueTest(table, Int.MinValue)


}
