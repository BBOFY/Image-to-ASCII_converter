package app.filters

import app.filters.specific.BrightnessFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

class BrightnessFilterTests extends FunSuite {

	val brightnessFilter = new BrightnessFilter

	val testImg0 = new ImageGrey(
		Vector(
			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
		)
	)

	val testImg1 = new ImageGrey(
		Vector(
			Vector(PixelGrey(100), PixelGrey(150), PixelGrey(96)),
			Vector(PixelGrey(42), PixelGrey(249), PixelGrey(169))
		)
	)

	test("Small increment") {
		val increment = 10
		brightnessFilter.setValue(increment)
		val refImg0 = new ImageGrey(
			Vector(
				Vector(PixelGrey(10), PixelGrey(11), PixelGrey(12)),
				Vector(PixelGrey(13), PixelGrey(14), PixelGrey(15))
			)
		)
		val refImg1 = new ImageGrey(
			Vector(
				Vector(PixelGrey(110), PixelGrey(160), PixelGrey(106)),
				Vector(PixelGrey(52), PixelGrey(255), PixelGrey(179))
			)
		)
		val img0 = brightnessFilter.apply(testImg0)
		val img1 = brightnessFilter.apply(testImg1)
		assert(img0 == refImg0)
		assert(img1 == refImg1)
	}

	test("Small decrement") {
		val decrement = -10
		brightnessFilter.setValue(decrement)
		val refImg0 = new ImageGrey(
			Vector(
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
			)
		)
		val refImg1 = new ImageGrey(
			Vector(
				Vector(PixelGrey(90), PixelGrey(140), PixelGrey(86)),
				Vector(PixelGrey(32), PixelGrey(239), PixelGrey(159))
			)
		)
		val img0 = brightnessFilter.apply(testImg0)
		val img1 = brightnessFilter.apply(testImg1)
		assert(img0 == refImg0)
		assert(img1 == refImg1)
	}

	test("Big increment") {
		val increment = 145
		brightnessFilter.setValue(increment)
		val refImg0 = new ImageGrey(
			Vector(
				Vector(PixelGrey(145), PixelGrey(146), PixelGrey(147)),
				Vector(PixelGrey(148), PixelGrey(149), PixelGrey(150))
			)
		)
		val refImg1 = new ImageGrey(
			Vector(
				Vector(PixelGrey(245), PixelGrey(255), PixelGrey(241)),
				Vector(PixelGrey(187), PixelGrey(255), PixelGrey(255))
			)
		)
		val img0 = brightnessFilter.apply(testImg0)
		val img1 = brightnessFilter.apply(testImg1)
		assert(img0 == refImg0)
		assert(img1 == refImg1)
	}

	test("Big decrement") {
		val decrement = -145
		brightnessFilter.setValue(decrement)
		val refImg0 = new ImageGrey(
			Vector(
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
			)
		)
		val refImg1 = new ImageGrey(
			Vector(
				Vector(PixelGrey(0), PixelGrey(5), PixelGrey(0)),
				Vector(PixelGrey(0), PixelGrey(104), PixelGrey(24))
			)
		)
		val img0 = brightnessFilter.apply(testImg0)
		val img1 = brightnessFilter.apply(testImg1)
		assert(img0 == refImg0)
		assert(img1 == refImg1)
	}

	test("Too big increment") {
		val increment = Int.MaxValue
		brightnessFilter.setValue(increment)
		val refImg0 = new ImageGrey(
			Vector(
				Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255)),
				Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255))
			)
		)
		val refImg1 = new ImageGrey(
			Vector(
				Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255)),
				Vector(PixelGrey(255), PixelGrey(255), PixelGrey(255))
			)
		)
		val img0 = brightnessFilter.apply(testImg0)
		val img1 = brightnessFilter.apply(testImg1)
		assert(img0 == refImg0)
		assert(img1 == refImg1)
	}

	test("Too big decrement") {
		val decrement = Int.MinValue
		brightnessFilter.setValue(decrement)
		val refImg0 = new ImageGrey(
			Vector(
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
			)
		)
		val refImg1 = new ImageGrey(
			Vector(
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0)),
				Vector(PixelGrey(0), PixelGrey(0), PixelGrey(0))
			)
		)
		val img0 = brightnessFilter.apply(testImg0)
		val img1 = brightnessFilter.apply(testImg1)
		assert(img0 == refImg0)
		assert(img1 == refImg1)
	}

}
