package app.filters.mixed

import app.filters.specific.{BrightnessFilter, FlipXFilter, FlipYFilter, InvertFilter, RotateFilter}
import app.filters.{FilterTests, IdentityImageFilter, ImageFilter}
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class MixedFilterTests extends FilterTests {

	protected val orgImg = new ImageGrey(Vector(
		Vector(PixelGrey(0), PixelGrey(50), PixelGrey(150), PixelGrey(200)),
		Vector(PixelGrey(30), PixelGrey(90), PixelGrey(180), PixelGrey(250))
	))

	protected val refImgRotated180 = new ImageGrey(Vector(
		Vector(PixelGrey(250), PixelGrey(180), PixelGrey(90), PixelGrey(30)),
		Vector(PixelGrey(200), PixelGrey(150), PixelGrey(50), PixelGrey(0))
	))

	protected val refImgInverted = new ImageGrey(Vector(
		Vector(PixelGrey(255), PixelGrey(205), PixelGrey(105), PixelGrey(55)),
		Vector(PixelGrey(225), PixelGrey(165), PixelGrey(75), PixelGrey(5))
	))

	protected val refImgFlippedXRotated90 = new ImageGrey(Vector(
		Vector(PixelGrey(250), PixelGrey(200)),
		Vector(PixelGrey(180), PixelGrey(150)),
		Vector(PixelGrey(90), PixelGrey(50)),
		Vector(PixelGrey(30), PixelGrey(0))
	))

	protected val refImgBright25Inverted = new ImageGrey(Vector(
		Vector(PixelGrey(230), PixelGrey(180), PixelGrey(80), PixelGrey(30)),
		Vector(PixelGrey(200), PixelGrey(140), PixelGrey(50), PixelGrey(0))
	))

	protected val refImgFlippedYRotated180InvertedRotated90FlippedX = new ImageGrey(Vector(
		Vector(PixelGrey(225), PixelGrey(255)),
		Vector(PixelGrey(165), PixelGrey(205)),
		Vector(PixelGrey(75), PixelGrey(105)),
		Vector(PixelGrey(5), PixelGrey(55))
	))

	protected val identity: IdentityImageFilter.type = IdentityImageFilter
	protected val flipperX = new FlipXFilter
	protected val flipperY = new FlipYFilter
	protected val inverter = new InvertFilter
	protected val brightener = new BrightnessFilter
	brightener.setValue(25)
	protected val rotator90 = new RotateFilter
	rotator90.setValue(90)
	protected val rotator180 = new RotateFilter
	rotator180.setValue(180)

	protected val rotateFilter: Seq[ImageFilter] = Seq(rotator180)
	protected val invertFilter: Seq[ImageFilter] = Seq(inverter)
	protected val flipRotateFilters: Seq[ImageFilter] = Seq(flipperX, rotator90)
	protected val brightnessInvertFilters: Seq[ImageFilter] = Seq(brightener, identity, inverter)
	protected val flipRotateInvertRotateFlipFilters: Seq[ImageFilter] = Seq(flipperY, rotator180, inverter, rotator90, flipperX)

	protected val emptyMF = new MixedFilter
	protected val rotationMF = new MixedFilter(rotateFilter)
	protected val invertMF = new MixedFilter(invertFilter)
	protected val flipRotateMF = new MixedFilter(flipRotateFilters)
	protected val brightnessInvertMF = new MixedFilter(brightnessInvertFilters)
	protected val flipRotateInvertRotateFlipMF = new MixedFilter(flipRotateInvertRotateFlipFilters)

	test("Identity only") {
		val img = emptyMF.apply(orgImg)
		assert(
			img.getGrid == orgImg.getGrid
		)
	}

	test("Rotation 180 only") {
		val img = rotationMF.apply(orgImg)
		assert(
			img.getGrid == refImgRotated180.getGrid
		)
	}

	test("Inverting only") {
		val img = invertMF.apply(orgImg)
		assert(
			img.getGrid == refImgInverted.getGrid
		)
	}

	test("Flip by X axis, rotate by 90") {
		val img = flipRotateMF.apply(orgImg)
		assert(
			img.getGrid == refImgFlippedXRotated90.getGrid
		)
	}

	test("Brighten by 25, identity, invert") {
		val img = brightnessInvertMF.apply(orgImg)
		assert(
			img.getGrid == refImgBright25Inverted.getGrid
		)
	}

	test("Flip by Y axis, rotate by 180, invert, rotate by 90, flip by X axis") {
		val img = flipRotateInvertRotateFlipMF.apply(orgImg)
		assert(
			img.getGrid == refImgFlippedYRotated180InvertedRotated90FlippedX.getGrid
		)
	}



}
