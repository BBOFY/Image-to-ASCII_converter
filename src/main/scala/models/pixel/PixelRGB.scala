package models.pixel

case class PixelRGB(private val r: Int, private val g: Int, private val b: Int) extends PixelNumeric[RGB] {

	@throws (classOf[IllegalArgumentException])
	private val _value: RGB = {
		RGB(r, g, b)
	}

	override def getValue: RGB = _value

	override def getGreyscale: Int = {
		((0.3 * _value.r) + (0.59 * _value.g) + (0.11 * _value.b)).toInt
	}

}
