package app.converters

import app.models.image.{ImageGrey, ImageRgb}
import app.models.pixel.{PixelGrey, PixelRgb}
import org.scalatest.FunSuite

class GreyScalerTests extends FunSuite {

	protected val greyScaler = new GreyScaler

	protected val orgImg = new ImageRgb(Vector(
		Vector(PixelRgb(0, 55, 12), PixelRgb(30, 142, 241), PixelRgb(60, 48, 216)),
		Vector(PixelRgb(120, 183, 98), PixelRgb(150, 95, 0), PixelRgb(180, 0, 54))
	))

	protected val refImg = new ImageGrey(Vector(
		Vector(PixelGrey(33), PixelGrey(119), PixelGrey(70)),
		Vector(PixelGrey(154), PixelGrey(101), PixelGrey(59))
	))

	test("Regular RGB to Grey") {
		val img = greyScaler.convert(orgImg)
		assert(img.getGrid == refImg.getGrid)
	}

	test("Edge case 0") {
		val imgRgb = new ImageRgb(Vector(
			Vector(PixelRgb(0, 0, 0))
		))
		val refGrey = new ImageGrey(Vector(
			Vector(PixelGrey(0))
		))
		val img = greyScaler.convert(imgRgb)
		assert(img.getGrid == refGrey.getGrid)
	}

	test("Edge case 1") {
		val imgRgb = new ImageRgb(Vector(
			Vector(PixelRgb(246, 246, 246))
		))
		val refGrey = new ImageGrey(Vector(
			Vector(PixelGrey(246))
		))
		val img = greyScaler.convert(imgRgb)
		assert(img.getGrid == refGrey.getGrid)
	}

	test("Edge case 2") {
		val imgRgb = new ImageRgb(Vector(
			Vector(PixelRgb(255, 255, 255))
		))
		val refGrey = new ImageGrey(Vector(
			Vector(PixelGrey(255))
		))
		val img = greyScaler.convert(imgRgb)
		assert(img.getGrid == refGrey.getGrid)
	}

}
