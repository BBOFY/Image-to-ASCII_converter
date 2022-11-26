package app.models.image

import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class ImageGreyTests extends FunSuite {

	val width: Int = 3
	val height: Int = 2

	val testingGrid: Vector[Vector[PixelGrey]] = Vector(
		Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
		Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
	)

	test("Valid image - Creating") {
		val image = new ImageGrey(width, height)

		for (i <- 0 until height) {
			image.setRow(i, testingGrid.apply(i))
		}

		assertResult(width) {
			image.width
		}

		assertResult(height) {
			image.height
		}

		assertResult(2) {
			image.getPixel(2, 0).value
		}

		assertResult(4) {
			image.getPixel(1, 1).value
		}

		assert(image.getColumn(2) == Vector(PixelGrey(2), PixelGrey(5)))
	}

	test ("Valid image - Changing rows") {
		val image = new ImageGrey(width, height)

		for (i <- 0 until height) {
			image.setRow(i, testingGrid.apply(i))
		}

		image.setRow(0, Vector( PixelGrey(100), PixelGrey(150), PixelGrey(200)))

		assertResult(100) {
			image.getRow(0).apply(0).value
		}
		assertResult(150) {
			image.getColumn(1).apply(0).value
		}
		assertResult(200) {
			image.getPixel(2, 0).value
		}
		// others should not be affected
		assertResult(5) {
			image.getPixel(2, 1).value
		}
		assertResult(4) {
			image.getPixel(1, 1).value
		}

		// row out of bounds
		assertThrows[IllegalArgumentException] {
			image.setRow(2, Vector( PixelGrey(100), PixelGrey(150), PixelGrey(200)))
		}
		// short replacement row
		assertThrows[IllegalArgumentException] {
			image.setRow(0, Vector(PixelGrey(100), PixelGrey(150)))
		}
		// wrong value inside replacement row
		assertThrows[IllegalArgumentException] {
			image.setRow(0, Vector(PixelGrey(100), PixelGrey(1500), PixelGrey(200)))
		}

	}

	test("Valid image - Changing columns") {
		val image = new ImageGrey(width, height)

		for (i <- 0 until height) {
			image.setRow(i, testingGrid.apply(i))
		}

		image.setColumn(1, Vector( PixelGrey(10), PixelGrey(20)))

		assertResult(10) {
			image.getRow(0).apply(1).value
		}
		assertResult(20) {
			image.getPixel(1, 1).value
		}

		assertResult(0) {
			image.getPixel(0, 0).value
		}
		assertResult(5) {
			image.getPixel(2, 1).value
		}

		// row out of bounds
		assertThrows[IllegalArgumentException] {
			image.setColumn(3, Vector( PixelGrey(10), PixelGrey(20)))
		}
		// long replacement row
		assertThrows[IllegalArgumentException] {
			image.setColumn(1, Vector( PixelGrey(10), PixelGrey(20), PixelGrey(30)))
		}
		// wrong value inside replacement row
		assertThrows[IllegalArgumentException] {
			image.setColumn(1, Vector( PixelGrey(1000), PixelGrey(20)))
		}
	}

	test("Valid image - Changing pixel") {
		val image = new ImageGrey(width, height)

		for (i <- 0 until height) {
			image.setRow(i, testingGrid.apply(i))
		}

		image.setPixel(1, 1, PixelGrey(255))

		assertResult(255) {
			image.getPixel(1, 1).value
		}

	}

//	private def initTestImage(w: Int, h: Int): ImageGrey = {
//
//	}

}
