package app.models.conversionTables

import org.scalatest.FunSuite

class BourkeTableTests extends FunSuite {
	private val table = BourkeTable.apply()

	validValueTest(0, '$')
	validValueTest(11, 'B')
	validValueTest(42, 'a')
	validValueTest(79, 'O')
	validValueTest(100, 'U')
	validValueTest(148, '/')
	validValueTest(193, '_')
	validValueTest(218, '!')
	validValueTest(255, ' ')


	invalidValueTest(-2)
	invalidValueTest(195318)
	invalidValueTest(Int.MaxValue)
	invalidValueTest(Int.MinValue)

	test("Whole image") {

	}

	private def validValueTest(initial: Int, expected: Char): Unit = {
		test(s"Valid value $initial") {
			assertResult(expected) {
				table.getTableValue(initial)
			}
		}
	}

	private def invalidValueTest(initial: Int): Unit = {
		test(s"Invalid value $initial") {
			assertThrows[IllegalArgumentException] {
				table.getTableValue(initial)
			}
		}
	}

}
