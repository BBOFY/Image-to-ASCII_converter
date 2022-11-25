package models.pixel

case class PixelRgb(private val r: Int, private val g: Int, private val b: Int) extends PixelNumeric[Rgb] {

	@throws (classOf[IllegalArgumentException])
	private val value_ : Rgb = Rgb(r, g, b)

	override def value: Rgb = value_

	override def getGreyscale: Int = {
		((0.3 * value_.r) + (0.59 * value_.g) + (0.11 * value_.b)).toInt
	}

}
