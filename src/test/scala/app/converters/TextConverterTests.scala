package app.converters

import app.models.image.ImageAscii
import app.models.pixel.PixelAscii
import org.scalatest.FunSuite

class TextConverterTests extends FunSuite {
	protected val textConverter = new TextConverter

	protected val orgDefaultImage = new ImageAscii(Vector(
		Vector(PixelAscii('a'), PixelAscii('L'), PixelAscii('`')),
		Vector(PixelAscii('1'), PixelAscii('b'), PixelAscii('t'))
	))

	protected val smallImg = new ImageAscii(Vector(
		Vector(PixelAscii(')'))
	))

	protected val refDefaultResult = "aL`\n1bt"
	protected val refSmallResult = ")"

	test("Regular image") {
		val str = textConverter.convert(orgDefaultImage)
		assert(str == refDefaultResult)
	}

	test("Small image") {
		val str = textConverter.convert(smallImg)
		assert(str == refSmallResult)
	}
}
