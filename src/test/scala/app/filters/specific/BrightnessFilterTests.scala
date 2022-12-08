package app.filters.specific

import app.filters.VariableFilterTests
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class BrightnessFilterTests extends VariableFilterTests {

	val brightnessFilter = new BrightnessFilter

	validFilterTest(
		"Small increment 0",
		brightnessFilter,
		_testImg0,
		new ImageGrey(Vector(
			Vector(PixelGrey(10), PixelGrey(11), PixelGrey(12)),
			Vector(PixelGrey(13), PixelGrey(14), PixelGrey(15))
		)),
		10
	)

	validFilterTest(
		"Small increment 1",
		brightnessFilter,
		_testImg1,
		new ImageGrey(Vector(
			Vector(PixelGrey(110), PixelGrey(160), PixelGrey(106)),
			Vector(PixelGrey(52), PixelGrey(255), PixelGrey(179))
		)),
		10
	)

	validFilterTest(
		"Small decrement 0",
		brightnessFilter,
		_testImg0,
		new ImageGrey(Vector(
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
		)),
		-10
	)

	validFilterTest(
		"Small decrement 1",
		brightnessFilter,
		_testImg1,
		new ImageGrey(Vector(
			Vector(PixelGrey(90), PixelGrey(140), PixelGrey(86)),
			Vector(PixelGrey(32), PixelGrey(239), PixelGrey(159))
		)),
		-10
	)

	validFilterTest(
		"Big increment 0",
		brightnessFilter,
		_testImg0,
		new ImageGrey(Vector(
			Vector(PixelGrey(145), PixelGrey(146), PixelGrey(147)),
			Vector(PixelGrey(148), PixelGrey(149), PixelGrey(150))
		)),
		145
	)

	validFilterTest(
		"Big increment 1",
		brightnessFilter,
		_testImg1,
		new ImageGrey(Vector(
			Vector(PixelGrey(245), PixelGrey(255), PixelGrey(241)),
			Vector(PixelGrey(187), PixelGrey(255), PixelGrey(255))
		)),
		145
	)

	validFilterTest(
		"Big decrement 0",
		brightnessFilter,
		_testImg0,
		new ImageGrey(Vector(
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
		)),
		-145
	)

	validFilterTest(
		"Big decrement 1",
		brightnessFilter,
		_testImg1,
		new ImageGrey(Vector(
			Vector(PixelGrey(0), PixelGrey(5), PixelGrey(0)),
			Vector(PixelGrey(0), PixelGrey(104), PixelGrey(24))
		)),
		-145
	)

	validFilterTest(
		"Too big increment 0",
		brightnessFilter,
		_testImg0,
		new ImageGrey(Vector(
			Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255)),
			Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255))
		)),
		Int.MaxValue
	)

	validFilterTest(
		"Too big increment 1",
		brightnessFilter,
		_testImg1,
		new ImageGrey(Vector(
			Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255)),
			Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255))
		)),
		Int.MaxValue
	)

	validFilterTest(
		"Too big decrement 0",
		brightnessFilter,
		_testImg0,
		new ImageGrey(Vector(
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
		)),
		Int.MinValue
	)

	validFilterTest(
		"Too big decrement 1",
		brightnessFilter,
		_testImg1,
		new ImageGrey(Vector(
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
			Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
		)),
		Int.MinValue
	)

	validFilterTest(
		"Zero increment",
		brightnessFilter,
		_testImg0,
		_testImg0,
		0
	)

	validFilterTest(
		"Negative zero increment",
		brightnessFilter,
		_testImg1,
		_testImg1,
		-0
	)

}
