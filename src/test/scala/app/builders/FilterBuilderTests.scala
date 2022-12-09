package app.builders

import app.filters.{ImageFilter, VariableFilter}
import app.filters.specific.{BrightnessFilter, FlipXFilter, FlipYFilter, InvertFilter, RotateFilter}
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class FilterBuilderTests extends FunSuite {
	protected val builder = new FilterBuilder

	protected val filterInv: ImageFilter = new InvertFilter
	protected val filterFlipX: ImageFilter = new FlipXFilter
	protected val filterFlipY: ImageFilter = new FlipYFilter
	protected val filterRotate90: VariableFilter = new RotateFilter
	filterRotate90.setValue(90)
	protected val filterRotate180: VariableFilter = new RotateFilter
	filterRotate180.setValue(180)
	protected val filterBrightness: VariableFilter = new BrightnessFilter
	filterBrightness.setValue(25)


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


}
