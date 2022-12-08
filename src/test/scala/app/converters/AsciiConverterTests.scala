package app.converters

import app.models.conversionTables.{ConstantTable, CustomTable}
import app.models.image.{ImageAscii, ImageGrey}
import app.models.pixel.{PixelAscii, PixelGrey}
import org.scalatest.FunSuite

class AsciiConverterTests extends FunSuite {

	protected val triConversionTable = new CustomTable
	triConversionTable.setValue("TRI")
	protected val constantConversionTable = new ConstantTable

	protected val defaultAsciiConverter = new AsciiConverter
	protected val triAsciiConverter = new AsciiConverter(triConversionTable)
	protected val constAsciiConverter = new AsciiConverter(constantConversionTable)

	protected val orgImg = new ImageGrey(Vector(
		Vector(PixelGrey(42), PixelGrey(91), PixelGrey(246)),
		Vector(PixelGrey(169), PixelGrey(53), PixelGrey(147))
	))

	protected val refDefaultImg = new ImageAscii(Vector(
		Vector(PixelAscii('a'), PixelAscii('L'), PixelAscii('`')),
		Vector(PixelAscii('1'), PixelAscii('b'), PixelAscii('t'))
	))

	protected val refTriAsciiImg = new ImageAscii(Vector(
		Vector(PixelAscii('T'), PixelAscii('T'), PixelAscii('R')),
		Vector(PixelAscii('R'), PixelAscii('T'), PixelAscii('R'))
	))

	protected val refConstAsciiImg = new ImageAscii(Vector(
		Vector(PixelAscii('s'), PixelAscii('s'), PixelAscii('s')),
		Vector(PixelAscii('s'), PixelAscii('s'), PixelAscii('s'))
	))

	test("Default (Bourke) linear table") {
		val img = defaultAsciiConverter.convert(orgImg)
		assert(img.getGrid == refDefaultImg.getGrid)
	}

	test("Tri custom linear table") {
		val img = triAsciiConverter.convert(orgImg)
		assert(img.getGrid == refTriAsciiImg.getGrid)
	}

	test("Constant table") {
		val img = constAsciiConverter.convert(orgImg)
		assert(img.getGrid == refConstAsciiImg.getGrid)
	}

}
