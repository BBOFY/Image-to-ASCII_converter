package models.pixel

case class PixelGrey(private val value: Int) extends PixelNumeric[Int] {

	private val _value: Int = {
		if ( 0 to 255 contains value ) value
		else
			throw new IllegalArgumentException("Argument 'value' must be between 0 to 255")
	}
	override def getValue: Int = _value

	override def getGreyscale: Int = _value
}
