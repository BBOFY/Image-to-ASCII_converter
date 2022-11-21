package models.pixelOLD

import java.security.InvalidParameterException

class PixelGrey( _value: Int ) extends Pixel[Int] {

	private val value: Int = {
		if ( 0 to 255 contains _value )
			_value
		else
			throw new InvalidParameterException("Value must be in range 0 to 255")
	}

	override def getValue: Int = value

	override def getGreyScale: Int = value
}
