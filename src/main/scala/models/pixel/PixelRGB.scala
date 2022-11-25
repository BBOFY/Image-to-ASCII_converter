package models.pixel

case class PixelRGB(private val r: Int, private val g: Int, private val b: Int) extends PixelNumeric[RGB] {

	@throws (classOf[IllegalArgumentException])
	private val value_ : RGB = RGB(r, g, b)

	override def value: RGB = value_

	override def getGreyscale: Int = {
		((0.3 * value_.r) + (0.59 * value_.g) + (0.11 * value_.b)).toInt
	}

}
