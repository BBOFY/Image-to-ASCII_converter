package app.models.pixel

final case class PixelGrey(private val _value: Int) extends Pixel {

	@throws (classOf[ExceptionInInitializerError])
	val value : Int = {
		if ( 0 to 255 contains _value ) _value
		else
			throw new ExceptionInInitializerError("Argument \'value\' must be between 0 to 255")
	}
}
