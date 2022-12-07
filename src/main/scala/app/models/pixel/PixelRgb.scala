package app.models.pixel

import app.models.utils.RgbValue

final case class PixelRgb(private val r: Int, private val g: Int, private val b: Int) extends Pixel {

	@throws (classOf[IllegalArgumentException])
	val value : RgbValue = RgbValue(r, g, b)
}
