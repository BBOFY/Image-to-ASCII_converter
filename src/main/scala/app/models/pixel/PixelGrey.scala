package app.models.pixel

/**
 * Class holding data about grey pixel
 *
 * @param _value Value of the grey pixel
 * @throws ExceptionInInitializerError If parameter is smaller than 0 or greater than 255
 */
final case class PixelGrey(private val _value: Int) extends Pixel {

	@throws (classOf[ExceptionInInitializerError])
	val value : Int = {
		if ( 0 to 255 contains _value ) _value
		else
			throw new ExceptionInInitializerError("Argument \'value\' must be between 0 to 255")
	}
}
