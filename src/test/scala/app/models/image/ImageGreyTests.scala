package app.models.image

import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class ImageGreyTests extends FunSuite {

	val testingGrid: Vector[Vector[PixelGrey]] = Vector(
		Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
		Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
	)

	val width: Int = testingGrid.head.length
	val height: Int = testingGrid.length

	test("Valid image") {
		val image = new ImageGrey(testingGrid)

		assertResult(3) {
			image.width
		}

		assertResult(2) {
			image.height
		}

		assertResult(2) {
			image.getPixel(2, 0).value
		}

		assertResult(4) {
			image.getPixel(1, 1).value
		}

		assert(
			image.getGrid == testingGrid
		)
	}

	test("Small image") {
		val smallGrid = Vector(Vector(PixelGrey(153)))

		val img = new ImageGrey(smallGrid)

		assertResult(1) {
			img.width
		}
		assertResult(1) {
			img.height
		}
		assert(
			img.getGrid == smallGrid
		)
		assertResult(153) {
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
			new ImageGrey(emptyGrid)
		}
	}

	test("Invalid image 1") {
		val gridEmptyRow = Vector(
			Vector.empty
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageGrey(gridEmptyRow)
		}
	}

	test("Invalid image 2") {
		val gridEmptyRow = Vector(
			Vector(),
			Vector(),
			Vector()
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageGrey(gridEmptyRow)
		}
	}

	test("Irregular image 0") {
		val irregularGrid = Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(PixelGrey(3), PixelGrey(4)),
			Vector(PixelGrey(5), PixelGrey(6), PixelGrey(7))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageGrey(irregularGrid)
		}
	}

	test("Irregular image 1") {
		val irregularGrid = Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5)),
			Vector(PixelGrey(6), PixelGrey(7), PixelGrey(8)),
			Vector(PixelGrey(91)),
			Vector(PixelGrey(56))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageGrey(irregularGrid)
		}
	}

	test("Empty row 0") {
		val withEmptyRow = Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5)),
			Vector(PixelGrey(6), PixelGrey(7), PixelGrey(8)),
			Vector.empty,
			Vector(PixelGrey(6), PixelGrey(7), PixelGrey(8))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageGrey(withEmptyRow)
		}
	}

	test("Empty row 1") {
		val withEmptyRow = Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(),
			Vector(PixelGrey(6), PixelGrey(7), PixelGrey(8)),
			Vector(PixelGrey(6), PixelGrey(7), PixelGrey(8))
		)
		assertThrows[ExceptionInInitializerError] {
			new ImageGrey(withEmptyRow)
		}
	}

}
