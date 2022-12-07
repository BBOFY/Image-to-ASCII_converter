package app.models.pixel

import org.scalatest.FunSuite

class PixelAsciiTests extends FunSuite {
	test("Pixel valid") {
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

	test("Pixel invalid") {
		assertThrows[IllegalArgumentException] {
			PixelAscii(961)
		}

		assertThrows[IllegalArgumentException] {
			PixelAscii('‰')
		}
	}
}
