package app.converters

import app.models.image.{ImageGrey, ImageRgb}
import app.models.pixel.{PixelGrey, PixelRgb}
import org.scalatest.FunSuite

class GreyScalerTests extends FunSuite {

	protected val orgImg = new ImageRgb(Vector(
		Vector(PixelRgb(0), PixelRgb(30), PixelRgb(60)),
		Vector(PixelRgb(120), PixelRgb(150), PixelRgb(180))
	))

	test("Regular RGB to Grey") {

	}

}
