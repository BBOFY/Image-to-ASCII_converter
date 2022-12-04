package app.models.pixel

final case class PixelGrey(private val _value: Int) extends Pixel {

	@throws (classOf[IllegalArgumentException])
	var value_ : Int = {
		if ( 0 to 255 contains _value ) _value
		else
			throw new IllegalArgumentException("Argument 'value' must be between 0 to 255")
	}

}
