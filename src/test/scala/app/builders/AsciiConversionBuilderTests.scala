package app.builders

import app.models.conversionTables.{AsciiTable, BourkeTable, ConstantTable, CustomTable}
import app.models.image.{ImageAscii, ImageGrey}
import app.models.pixel.{PixelAscii, PixelGrey}
import org.scalatest.FunSuite

class AsciiConversionBuilderTests extends FunSuite {
	protected val builder = new AsciiConversionBuilder

	protected val customTable: CustomTable = CustomTable.apply()
	customTable.setValue("TRI")

	protected val tableBourke: AsciiTable = BourkeTable.apply()
	protected val tableNonlinear: AsciiTable = ConstantTable.apply()
	protected val tableCustom: AsciiTable = customTable


	protected val orgImg = new ImageGrey(Vector(
		Vector(PixelGrey(42), PixelGrey(91), PixelGrey(246)),
		Vector(PixelGrey(169), PixelGrey(53), PixelGrey(147))
	))

	protected val refDefaultImg = new ImageAscii(Vector(
		Vector(PixelAscii('a'), PixelAscii('L'), PixelAscii('`')),
		Vector(PixelAscii('1'), PixelAscii('b'), PixelAscii('t'))
	))

	protected val refCustomAsciiImg = new ImageAscii(Vector(
		Vector(PixelAscii('T'), PixelAscii('T'), PixelAscii('R')),
		Vector(PixelAscii('R'), PixelAscii('T'), PixelAscii('R'))
	))

	protected val refConstAsciiImg = new ImageAscii(Vector(
		Vector(PixelAscii('s'), PixelAscii('s'), PixelAscii('s')),
		Vector(PixelAscii('s'), PixelAscii('s'), PixelAscii('s'))
	))

	test("Default builder") {
		val converter = builder.build
		val img = converter.convert(orgImg)
		assert(
			img.getGrid == refDefaultImg.getGrid
		)
	}

	test("With bourke builder") {
		builder.registerProperty(tableBourke)
		val converter = builder.build
		val img = converter.convert(orgImg)
		assert(
			img.getGrid == refDefaultImg.getGrid
		)
	}

	test("With nonlinear builder") {
		builder.registerProperty(tableNonlinear)
		val converter = builder.build
		val img = converter.convert(orgImg)
		assert(
			img.getGrid == refConstAsciiImg.getGrid
		)
	}


	test("With custom builder") {
		builder.registerProperty(tableCustom)
		val converter = builder.build
		val img = converter.convert(orgImg)
		assert(
			img.getGrid == refCustomAsciiImg.getGrid
		)
	}


}
