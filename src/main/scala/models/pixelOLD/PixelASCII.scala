package models.pixelOLD

import java.security.InvalidParameterException

class PixelASCII( _value: Char ) extends Pixel[Char] {

	private val value: Char = {
		if ( !(0 to 127 contains _value) ) throw new InvalidParameterException("Value must be an ASCII character")
		_value
	}

	override def getValue: Char = value

	override def getGreyScale: Int = throw new NotImplementedError("ASCII pixel cannot convert back to numerical grayscale value")
}
