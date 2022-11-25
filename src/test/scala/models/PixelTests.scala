package models

import models.pixel.{PixelGrey, PixelRGB, PixelAscii}
import org.scalatest.FunSuite

import java.security.InvalidParameterException

class PixelTests extends FunSuite {

	test("Grey pixel valid") {

		for ( i <- 0 to 255 ) {
			val pixel = PixelGrey(i)
			assertResult(i) {
				pixel.getValue
			}
			assertResult(i) {
				pixel.getValue
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

		val pixel = PixelRGB(r, g, b)

		assertResult(r) {
			pixel.getValue.r
		}
		assertResult(g) {
			pixel.getValue.g
		}
		assertResult(b) {
			pixel.getValue.b
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
			val pixel = PixelRGB(900, 12, -5)
		}
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRGB(100, -12, 5)
		}
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRGB(-100, -12, -5)
		}
		assertThrows[IllegalArgumentException] {
			val pixel = PixelRGB(1623, 256, 633)
		}
	}

	test("ASCII pixel valid") {
		for (i: Char <- 0.toChar to 127.toChar) {
			val pixel = PixelAscii(i)
			assertResult(i) {
				pixel.getValue
			}
		}

		assertResult('i') {
			var pixel = PixelAscii('i')
			pixel.getValue
		}
		assertResult('+') {
			var pixel = PixelAscii(43)
			pixel.getValue
		}
		assertResult(126) {
			var pixel = PixelAscii('~')
			pixel.getValue
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