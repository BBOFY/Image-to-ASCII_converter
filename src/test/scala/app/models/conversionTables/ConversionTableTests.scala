package app.models.conversionTables

import org.scalatest.FunSuite

abstract class ConversionTableTests extends FunSuite {

	protected def validValueTest(table: AsciiTable, initial: Int, expected: Char): Unit = {
		test(s"Valid value $initial") {
			assertResult(expected) {
				table.getTableValue(initial)
			}
		}
	}

	protected def invalidValueTest(table: AsciiTable, initial: Int): Unit = {
		test(s"Invalid value $initial") {
			assertThrows[IllegalArgumentException] {
				table.getTableValue(initial)
			}
		}
	}
}
