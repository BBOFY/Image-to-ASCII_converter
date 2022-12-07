package app.models.pixel

import org.scalatest.FunSuite

class PixelGreyTests extends FunSuite {
	test("Pixel valid") {

		for (i <- 0 to 255) {
			val pixel = PixelGrey(i)
			assertResult(i) {
				pixel.value
			}
			assertResult(i) {
				pixel.value
			}
		}
	}

	test("Pixel invalid") {

		assertThrows[IllegalArgumentException] {
			PixelGrey(-5)
		}

		assertThrows[IllegalArgumentException] {
			PixelGrey(256)
		}
	}
}
