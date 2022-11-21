package models.pixelOLD

import models.utils.ValueRGB

import java.security.InvalidParameterException

class PixelRGB( _r: Int, _g: Int, _b: Int ) extends Pixel[ValueRGB] {

	private val value: ValueRGB = {
		if ( !(0 to 255 contains _r)
			|| !(0 to 255 contains _g)
			|| !(0 to 255 contains _b)
		) throw new InvalidParameterException("All values must be in range 0 to 255")

		ValueRGB(_r, _g, _b)
	}

	override def getValue: ValueRGB = value

	override def getGreyScale: Int = {
		((0.3 * value.r) + (0.59 * value.g) + (0.11 * value.b)).toInt
	}
}
