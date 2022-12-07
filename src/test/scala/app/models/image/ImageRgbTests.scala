package app.models.image

import app.models.pixel.{PixelRgb, RgbValue}
import org.scalatest.FunSuite

class ImageRgbTests extends FunSuite {
	val testingGrid: Vector[Vector[PixelRgb]] = Vector(
		Vector(PixelRgb(0, 0, 0), PixelRgb(1, 5, 9), PixelRgb(2, 9, 0)),
		Vector(PixelRgb(3, 3, 3), PixelRgb(4, 15, 94), PixelRgb(5, 41, 15))
	)

	val width: Int = testingGrid.head.length
	val height: Int = testingGrid.length

	test("Valid image") {
		val image = new ImageRgb(testingGrid)

		assertResult(3) {
			image.width
		}

		assertResult(2) {
			image.height
		}

		assertResult(RgbValue(2, 9, 0)) {
			image.getPixel(2, 0).value
		}

		assertResult(RgbValue(4, 15, 94)) {
			image.getPixel(1, 1).value
		}

		assert(
			image.getGrid == testingGrid
		)
	}

	test("Small image") {
		val smallGrid = Vector(Vector(PixelRgb(69, 42, 23)))

		val img = new ImageRgb(smallGrid)

		assertResult(1) {
			img.width
		}
		assertResult(1) {
			img.height
		}
		assert(
			img.getGrid == smallGrid
		)
		assertResult(RgbValue(69, 42, 23)) {
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
			new ImageRgb(emptyGrid)
		}
	}

	test("Invalid image 1") {
		val gridEmptyRow = Vector(
			Vector.empty
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageRgb(gridEmptyRow)
		}
	}

	test("Irregular image 0") {
		val irregularGrid = Vector(
			Vector(PixelRgb(0, 55, 1), PixelRgb(1, 87, 29), PixelRgb(87, 29, 63)),
			Vector(PixelRgb(3, 98, 165), PixelRgb(51, 54, 5)),
			Vector(PixelRgb(5, 91, 6), PixelRgb(6, 54, 0), PixelRgb(7 ,3, 98))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageRgb(irregularGrid)
		}
	}

	test("Irregular image 1") {
		val irregularGrid = Vector(
			Vector(PixelRgb(0, 55, 1), PixelRgb(9, 1, 95), PixelRgb(7 ,3, 98)),
			Vector(PixelRgb(5, 91, 6), PixelRgb(87, 29, 63), PixelRgb(4, 81, 6)),
			Vector(PixelRgb(7 ,3, 98), PixelRgb(6, 54, 0), PixelRgb(98, 81, 74)),
			Vector(PixelRgb(1, 87, 29)),
			Vector(PixelRgb(6, 54, 0))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageRgb(irregularGrid)
		}
	}

	test("Empty row") {
		val withEmptyRow = Vector(
			Vector(PixelRgb(0, 55, 1), PixelRgb(9, 1, 95), PixelRgb(7, 3, 98)),
			Vector(PixelRgb(5, 91, 6), PixelRgb(87, 29, 63), PixelRgb(4, 81, 6)),
			Vector.empty,
			Vector(PixelRgb(7, 3, 98), PixelRgb(6, 54, 0), PixelRgb(98, 81, 74)),
			Vector(PixelRgb(0, 55, 1), PixelRgb(1, 87, 29), PixelRgb(87, 29, 63))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageRgb(withEmptyRow)
		}
	}
}
