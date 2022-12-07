package app.filters

import app.filters.specific.{FlipXFilter, FlipYFilter}
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class FlipFilterTests extends FunSuite {

	val flipperX = new FlipXFilter
	val flipperY = new FlipYFilter

	val testImg = new ImageGrey(
		Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
		)
	)

	val refImgFlippedX = new ImageGrey(
		Vector(
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5)),
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2))
		)
	)

	val refImgFlippedY = new ImageGrey(
		Vector(
			Vector(PixelGrey(2), PixelGrey(1), PixelGrey(0)),
			Vector(PixelGrey(5), PixelGrey(4), PixelGrey(3))
		)
	)

	val refImgFlippedXY = new ImageGrey(
		Vector(
			Vector(PixelGrey(5), PixelGrey(4), PixelGrey(3)),
			Vector(PixelGrey(2), PixelGrey(1), PixelGrey(0))
		)
	)

	test("Flip X regular") {
		val flippedImg = flipperX.apply(testImg)
		assert(refImgFlippedX == flippedImg)
	}

	test("Flip Y regular") {
		val flippedImg = flipperY.apply(testImg)
		assert(refImgFlippedY == flippedImg)
	}

	test("Flip X and Y regular") {
		val flippedXImg = flipperX.apply(testImg)
		val flippedYImg = flipperY.apply(flippedXImg)
		assert(refImgFlippedXY == flippedYImg)
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
	val refImgLongFlippedX = new ImageGrey(
		Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2), PixelGrey(3), PixelGrey(4), PixelGrey(5), PixelGrey(6), PixelGrey(7), PixelGrey(8), PixelGrey(9))
		)
	)
	val refImgLongFlippedY = new ImageGrey(
		Vector(
			Vector(PixelGrey(9), PixelGrey(8), PixelGrey(7), PixelGrey(6), PixelGrey(5), PixelGrey(4), PixelGrey(3), PixelGrey(2), PixelGrey(1), PixelGrey(0))
		)
	)

	test("FlipX Long") {
		val testImgLongFlipX = flipperX.apply(testImgLong)
		assert(testImgLongFlipX == refImgLongFlippedX)
	}

	test("FlipY Long") {
		val testImgLongFlipY = flipperY.apply(testImgLong)
		assert(testImgLongFlipY == refImgLongFlippedY)
	}

	val testImgTall = new ImageGrey(
		Vector(
			Vector(PixelGrey(0)),
			Vector(PixelGrey(1)),
			Vector(PixelGrey(2)),
			Vector(PixelGrey(3)),
			Vector(PixelGrey(4)),
			Vector(PixelGrey(5)),
			Vector(PixelGrey(6)),
			Vector(PixelGrey(7)),
			Vector(PixelGrey(8)),
			Vector(PixelGrey(9))
		)
	)
	val refImgTallFlippedX = new ImageGrey(
		Vector(
			Vector(PixelGrey(9)),
			Vector(PixelGrey(8)),
			Vector(PixelGrey(7)),
			Vector(PixelGrey(6)),
			Vector(PixelGrey(5)),
			Vector(PixelGrey(4)),
			Vector(PixelGrey(3)),
			Vector(PixelGrey(2)),
			Vector(PixelGrey(1)),
			Vector(PixelGrey(0))
		)
	)
	val refImgTallFlippedY = new ImageGrey(
		Vector(
			Vector(PixelGrey(0)),
			Vector(PixelGrey(1)),
			Vector(PixelGrey(2)),
			Vector(PixelGrey(3)),
			Vector(PixelGrey(4)),
			Vector(PixelGrey(5)),
			Vector(PixelGrey(6)),
			Vector(PixelGrey(7)),
			Vector(PixelGrey(8)),
			Vector(PixelGrey(9))
		)
	)

	test("FlipX Tall") {
		val testImgTallFlipX = flipperX.apply(testImgTall)
		assert(testImgTallFlipX == refImgTallFlippedX)
	}

	test("FlipY Tall") {
		val testImgTallFlipY = flipperY.apply(testImgTall)
		assert(testImgTallFlipY == refImgTallFlippedY)
	}

}
