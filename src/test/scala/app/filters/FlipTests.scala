package app.filters

import app.filters.specific.FlipFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class FlipTests extends FunSuite {

	val flipperX = new FlipFilter("x")
	val flipperY = new FlipFilter("y")

	val testImg: ImageGrey = new ImageGrey(Vector(
				Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
				Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
	))

	val testImgFlipX: ImageGrey = new ImageGrey(Vector(
		Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5)),
		Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2))
	))

	val testImgFlipY: ImageGrey = new ImageGrey(Vector(
		Vector(PixelGrey(2), PixelGrey(1), PixelGrey(0)),
		Vector(PixelGrey(5), PixelGrey(4), PixelGrey(3))
	))

	test("Flip X ") {
		val flippedImg = flipperX.apply(testImg)

		assert(testImgFlipX == flippedImg)
	}

	test("Flip Y") {
		val flippedImg = flipperY.apply(testImg)

		assert(testImgFlipY == flippedImg)
	}

//	val testingImage():unit

}
