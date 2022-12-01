package app.models.pixel

final case class PixelAscii(private val _value: Char) extends PixelText[Char] {

	@throws (classOf[IllegalArgumentException])
	private val value_ : Char = {
		if ( 0 to 127 contains _value ) _value
		else
			throw new IllegalArgumentException("Argument 'value' must be valid regular ASCII character with decimal value between 0 to 127")
	}

	def value: Char = value_
}
