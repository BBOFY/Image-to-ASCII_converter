package models.pixel

case class PixelAscii(private val value: Char) extends PixelText[Char] {

	private val value_ : Char = {
		if ( 0 to 127 contains value ) value
		else
			throw new IllegalArgumentException("Argument 'value' must be valid regular ASCII character with decimal value between 0 to 127")
	}

	override def getValue: Char = value_
}
