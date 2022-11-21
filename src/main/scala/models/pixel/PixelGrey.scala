package models.pixel

class PixelGrey(value: Int) extends PixelNumeric[Int] {

	private val _value: Int = {
		if ( 0 to 255 contains value ) value
		else
			throw new IllegalArgumentException("Argument 'value' must be between 0 to 255")
	}

	override def value: Int = _value
	override def getGreyscale(): Int = _value

}
