package app.filters.specific

import app.filters.FilterTests
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class InvertFilterTests extends FilterTests {
	protected val invertFilter = new InvertFilter

	protected val refImgInverted0 = new ImageGrey(Vector(
		Vector(PixelGrey(255), PixelGrey(254), PixelGrey(253)),
		Vector(PixelGrey(252), PixelGrey(251), PixelGrey(250))
	))

	protected val refImgInverted1 = new ImageGrey(Vector(
		Vector(PixelGrey(155), PixelGrey(105), PixelGrey(159)),
		Vector(PixelGrey(213), PixelGrey(6), PixelGrey(86))
	))

	test("Valid image 0") {
		val img = invertFilter.apply(_testImg0)
		assert(
			img.getGrid == refImgInverted0.getGrid
		)
	}

	test("Valid image 1") {
		val img = invertFilter.apply(_testImg1)
		assert(
			img.getGrid == refImgInverted1.getGrid
		)
	}

	test("Edge cases 0") {
		val orgImg = new ImageGrey(Vector(Vector(PixelGrey(0))))
		val refImg = new ImageGrey(Vector(Vector(PixelGrey(255))))
		val img = invertFilter.apply(orgImg)
		assert(
			img.getGrid == refImg.getGrid
		)
	}

	test("Edge cases 255") {
		val orgImg = new ImageGrey(Vector(Vector(PixelGrey(255))))
		val refImg = new ImageGrey(Vector(Vector(PixelGrey(0))))
		val img = invertFilter.apply(orgImg)
		assert(
			img.getGrid == refImg.getGrid
		)
	}
}
