package app.builders

import app.filters.mixed.MixedFilter
import app.filters.{IdentityImageFilter, ImageFilter, VariableFilter}
import app.filters.specific.{BrightnessFilter, FlipXFilter, FlipYFilter, InvertFilter, RotateFilter}
import org.scalatest.FunSuite

class FilterBuilderTests extends FunSuite {

	protected val identity: ImageFilter = IdentityImageFilter
	protected val flipperX: ImageFilter = new FlipXFilter
	protected val flipperY: ImageFilter = new FlipYFilter
	protected val inverter: ImageFilter = new InvertFilter
	protected val brightener: VariableFilter = new BrightnessFilter
	brightener.setValue(25)
	protected val rotator90: VariableFilter = new RotateFilter
	rotator90.setValue(90)
	protected val rotator180: VariableFilter = new RotateFilter
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

	test("Default filter") {
		val builder = new FilterBuilder
		assert(builder.build == emptyMF)
	}
	test("Simple filter 0") {
		val builder = new FilterBuilder
		builder.registerProperty(rotator180)
		assert(builder.build == rotationMF)
	}
	test("Simple filter 1") {
		val builder = new FilterBuilder
		builder.registerProperty(inverter)
		assert(builder.build == invertMF)
	}
	test("Flip Rotate") {
		val builder = new FilterBuilder
		builder.registerProperty(flipperX)
		builder.registerProperty(rotator90)
		assert(builder.build == flipRotateMF)
	}
	test("Bright Identity Invert") {
		val builder = new FilterBuilder
		builder.registerProperty(brightener)
		builder.registerProperty(identity)
		builder.registerProperty(inverter)
		assert(builder.build == brightnessInvertMF)
	}
	test("Complex Filter") {
		val builder = new FilterBuilder
		builder.registerProperty(flipperY)
		builder.registerProperty(rotator180)
		builder.registerProperty(inverter)
		builder.registerProperty(rotator90)
		builder.registerProperty(flipperX)
		assert(builder.build == flipRotateInvertRotateFlipMF)
	}

	test("Complex Filter identity removed") {
		val builder = new FilterBuilder
		builder.registerProperty(flipperY)
		builder.registerProperty(rotator180)
		builder.registerProperty(rotator90)
		builder.registerProperty(flipperX)
		assert(builder.build != flipRotateInvertRotateFlipMF)
	}

}
