package app.models.image

import app.models.pixel.PixelAscii
import org.scalatest.FunSuite

class ImageAsciiTests extends FunSuite {

	val testingGrid: Vector[Vector[PixelAscii]] = Vector(
		Vector(PixelAscii('0'), PixelAscii('1'), PixelAscii('2')),
		Vector(PixelAscii('3'), PixelAscii('4'), PixelAscii('5'))
	)

	val width: Int = testingGrid.head.length
	val height: Int = testingGrid.length

	test("Valid image") {
		val image = new ImageAscii(testingGrid)

		assertResult(3) {
			image.width
		}

		assertResult(2) {
			image.height
		}

		assertResult('2') {
			image.getPixel(2, 0).value
		}

		assertResult('4') {
			image.getPixel(1, 1).value
		}

		assert(
			image.getGrid == testingGrid
		)
	}

	test("Small image") {
		val smallGrid = Vector(Vector(PixelAscii('A')))

		val img = new ImageAscii(smallGrid)

		assertResult(1) {
			img.width
		}
		assertResult(1) {
			img.height
		}
		assert(
			img.getGrid == smallGrid
		)
		assertResult('A') {
			img.getPixel(0, 0).value
		}
		assertThrows[IndexOutOfBoundsException] {
			img.getPixel(1, 0).value
		}
		assertThrows[IndexOutOfBoundsException] {
			img.getPixel(0, 1).value
		}
		assertThrows[IndexOutOfBoundsException] {
			img.getPixel(1, 0).value
		}
	}

	test("Invalid image 0") {
		val emptyGrid = Vector.empty
		assertThrows[ExceptionInInitializerError] {
			new ImageAscii(emptyGrid)
		}
	}

	test("Invalid image 1") {
		val gridEmptyRow = Vector(
			Vector.empty
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageAscii(gridEmptyRow)
		}
	}

	test("Irregular image 0") {
		val irregularGrid = Vector(
			Vector(PixelAscii(0), PixelAscii(1), PixelAscii(2)),
			Vector(PixelAscii(3), PixelAscii(4)),
			Vector(PixelAscii(5), PixelAscii(6), PixelAscii(7))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageAscii(irregularGrid)
		}
	}

	test("Irregular image 1") {
		val irregularGrid = Vector(
			Vector(PixelAscii(0), PixelAscii('1'), PixelAscii(2)),
			Vector(PixelAscii('3'), PixelAscii(' '), PixelAscii('5')),
			Vector(PixelAscii('6'), PixelAscii('7'), PixelAscii(8)),
			Vector(PixelAscii(91)),
			Vector(PixelAscii(56))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageAscii(irregularGrid)
		}
	}

	test("Empty row") {
		val withEmptyRow = Vector(
			Vector(PixelAscii(0), PixelAscii('1'), PixelAscii(2)),
			Vector(PixelAscii('3'), PixelAscii(' '), PixelAscii('5')),
			Vector(PixelAscii('6'), PixelAscii('7'), PixelAscii(8)),
			Vector.empty,
			Vector(PixelAscii('3'), PixelAscii('4'), PixelAscii('5'))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageAscii(withEmptyRow)
		}
	}

}
