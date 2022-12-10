package app.models.pixel

/**
 * Class holding data about ascii pixel
 *
 * @param _value Value of the ascii pixel, basically just valid ASCII character
 * @throws ExceptionInInitializerError If parameter is smaller than 0 or greater than 127 (is not included in regular ASCII table)
 */
final case class PixelAscii(private val _value: Char) extends Pixel {

	@throws (classOf[ExceptionInInitializerError])
	var value : Char = {
		if ( 0 to 127 contains _value ) _value
		else
			throw new ExceptionInInitializerError("Argument \'value\' must be valid regular ASCII character with decimal value between 0 to 127")
	}
}
