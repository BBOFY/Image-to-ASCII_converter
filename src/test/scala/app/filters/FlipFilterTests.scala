package app.filters

import app.filters.specific.FlipFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class FlipFilterTests extends FunSuite {

	val flipperX = new FlipFilter("x")
	val flipperY = new FlipFilter("y")

	val testImg = new ImageGrey(
		Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
		)
	)

	val testImgFlipX = new ImageGrey(
		Vector(
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5)),
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2))
		)
	)

	val testImgFlipY = new ImageGrey(
		Vector(
			Vector(PixelGrey(2), PixelGrey(1), PixelGrey(0)),
			Vector(PixelGrey(5), PixelGrey(4), PixelGrey(3))
		)
	)

	test("Flip X regular") {
		val flippedImg = flipperX.apply(testImg)
		assert(testImgFlipX == flippedImg)
	}

	test("Flip Y regular") {
		val flippedImg = flipperY.apply(testImg)
		assert(testImgFlipY == flippedImg)
	}


	val testImgSmall = new ImageGrey(
		Vector(
			Vector(PixelGrey(42))
		)
	)

	test("FlipX 1x1") {
		val flippedSmallImg = flipperX.apply(testImgSmall)
		assert(flippedSmallImg == testImgSmall)
	}

	test("FlipY 1x1") {
		val flippedSmallImg = flipperY.apply(testImgSmall)
		assert(flippedSmallImg == testImgSmall)
	}

	val testImgLong = new ImageGrey(
		Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2), PixelGrey(3), PixelGrey(4), PixelGrey(5), PixelGrey(6), PixelGrey(7), PixelGrey(8), PixelGrey(9))
		)
	)
	val testImgLongFlippedY = new ImageGrey(
		Vector(
			Vector(PixelGrey(9), PixelGrey(8), PixelGrey(7), PixelGrey(6), PixelGrey(5), PixelGrey(4), PixelGrey(3), PixelGrey(2), PixelGrey(1), PixelGrey(0))
		)
	)

	test("FlipX 10x1") {
		val testImgLongFlipX = flipperX.apply(testImgLong)
		assert(testImgLongFlipX == testImgLong)
	}

	test("FlipY 10x1") {
		val testImgLongFlipY = flipperY.apply(testImgLong)
		assert(testImgLongFlipY == testImgLongFlippedY)
	}

}
