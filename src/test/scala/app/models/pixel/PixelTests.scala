package app.models.pixel

import app.models.pixel.{PixelAscii, PixelGrey, PixelRgb}
import org.scalatest.FunSuite

class PixelTests extends FunSuite {

	test("Grey pixel valid") {

		for ( i <- 0 to 255 ) {
			val pixel = PixelGrey(i)
			assertResult(i) {
				pixel.value
			}
			assertResult(i) {
				pixel.value
			}
		}
	}

	test("Grey pixel invalid") {

		assertThrows[IllegalArgumentException] {
			val pixel = PixelGrey(-5)
		}

		assertThrows[IllegalArgumentException] {
			val pixel = PixelGrey(256)
		}
	}

	test("RGB pixel valid") {
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

		def getGreyscaleFromRGB( r0: Int, g0: Int, b0: Int ): Int = {
			((0.3 * r0) + (0.59 * g0) + (0.11 * b0)).toInt
		}

		assertResult(getGreyscaleFromRGB(r, g, b)) {
			pixel.getGreyscale
		}
	}

	test("RGB pixel invalid") {
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRgb(900, 12, -5)
		}
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRgb(100, -12, 5)
		}
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRgb(-100, -12, -5)
		}
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRgb(1623, 256, 633)
		}
	}

	test("ASCII pixel valid") {
		for (i: Char <- 0.toChar to 127.toChar) {
			val pixel = PixelAscii(i)
			assertResult(i) {
				pixel.value
			}
		}

		assertResult('i') {
			var pixel = PixelAscii('i')
			pixel.value
		}
		assertResult('+') {
			var pixel = PixelAscii(43)
			pixel.value
		}
		assertResult(126) {
			var pixel = PixelAscii('~')
			pixel.value
		}
	}

	test("ASCII pixel invalid") {
		assertThrows[IllegalArgumentException] {
			val pixel = PixelAscii(961)
		}

		assertThrows[IllegalArgumentException] {
			val pixel = PixelAscii('‰')
		}
	}

}
