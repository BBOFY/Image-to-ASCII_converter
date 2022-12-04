package app.models.pixel

final case class PixelAscii(private val _value: Char) extends Pixel {

	@throws (classOf[IllegalArgumentException])
	var value_ : Char = {
		if ( 0 to 127 contains _value ) _value
		else
			throw new IllegalArgumentException("Argument 'value' must be valid regular ASCII character with decimal value between 0 to 127")
	}
}
