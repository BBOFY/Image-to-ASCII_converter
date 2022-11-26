package app.models.pixel

case class PixelGrey(private val _value: Int) extends PixelNumeric[Int] {

	@throws (classOf[IllegalArgumentException])
	private val value_ : Int = {
		if ( 0 to 255 contains _value ) _value
		else
			throw new IllegalArgumentException("Argument 'value' must be between 0 to 255")
	}
	override def value: Int = value_

	override def getGreyscale: Int = value_
}
