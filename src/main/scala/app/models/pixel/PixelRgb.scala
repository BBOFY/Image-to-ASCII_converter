package app.models.pixel

import app.models.utils.RgbValue

final case class PixelRgb(private val r: Int, private val g: Int, private val b: Int) extends Pixel {

	@throws (classOf[IllegalArgumentException])
	var value_ : RgbValue = RgbValue(r, g, b)

	override def getGreyScale: Int = {
		((0.3 * value_.r) + (0.59 * value_.g) + (0.11 * value_.b)).toInt
	}
}
