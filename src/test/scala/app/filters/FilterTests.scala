package app.filters

import app.models.image.ImageGrey
import app.models.pixel.PixelGrey
import org.scalatest.FunSuite

abstract class FilterTests extends FunSuite {

	/**
	 * Image 3x2 consisting of grey pixels with following values:
	 * [ [0, 1, 2],
	 * [3, 4, 5] ]
	 */
	protected val _testImg0 = new ImageGrey(Vector(
		Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
		Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
	))

	/**
	 * Image 3x2 consisting of grey pixels with following values:
	 * [ [100, 150, 96],
	 * [42, 249, 169] ]
	 */
	protected val _testImg1 = new ImageGrey(Vector(
		Vector(PixelGrey(100), PixelGrey(150), PixelGrey(96)),
		Vector(PixelGrey(42), PixelGrey(249), PixelGrey(169))
	))
}
