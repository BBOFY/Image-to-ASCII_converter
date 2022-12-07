package app.models.pixel

import org.scalatest.FunSuite

class PixelRgbTests extends FunSuite {
	test("Pixel valid") {
		val r = 8
		val g = 234
		val b = 186

		val pixel = PixelRgb(r, g, b)

		assertResult(r) {
			pixel.value.r
		}
		assertResult(g) {
			pixel.value.g
		}
		assertResult(b) {
			pixel.value.b
		}
	}

	test("Pixel invalid") {
		assertThrows[IllegalArgumentException] {
			PixelRgb(900, 12, -5)
		}
		assertThrows[IllegalArgumentException] {
			PixelRgb(100, -12, 5)
		}
		assertThrows[IllegalArgumentException] {
			PixelRgb(-100, -12, -5)
		}
		assertThrows[IllegalArgumentException] {
			PixelRgb(1623, 256, 633)
		}
	}
}
