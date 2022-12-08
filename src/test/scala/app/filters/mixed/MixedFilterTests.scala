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

	// rot 90 - flip X

	protected val refImgFlippedYRotated180InvertedRotated90FlippedX = new ImageGrey(Vector(
		Vector(PixelGrey(225), PixelGrey(165), PixelGrey(75), PixelGrey(5)),
		Vector(PixelGrey(255), PixelGrey(205), PixelGrey(105), PixelGrey(55))

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
	rotator90.setValue(180)

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





}
