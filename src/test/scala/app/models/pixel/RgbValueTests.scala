package app.models.pixel

import org.scalatest.FunSuite

class RgbValueTests extends FunSuite {

	test("Valid value") {
		val rgb = RgbValue(48, 216, 16)
		assertResult(48) {
			rgb.r
		}
		assertResult(216) {
			rgb.g
		}
		assertResult(16) {
			rgb.b
		}
	}

	test("Invalid red value") {
		assertThrows[ExceptionInInitializerError](
			RgbValue(519, 216, 16)
		)
		assertThrows[ExceptionInInitializerError](
			RgbValue(-19, 216, 16)
		)
	}

	test("Invalid green value") {
		assertThrows[ExceptionInInitializerError](
			RgbValue(48, 316, 16)
		)
		assertThrows[ExceptionInInitializerError](
			RgbValue(48, -96, 16)
		)
	}

	test("Invalid blue value") {
		assertThrows[ExceptionInInitializerError](
			RgbValue(48, 216, 1993)
		)
		assertThrows[ExceptionInInitializerError](
			RgbValue(48, 216, -16)
		)
	}

}
