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




}
