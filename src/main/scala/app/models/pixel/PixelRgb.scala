package app.models.pixel

final case class PixelRgb(private val r: Int, private val g: Int, private val b: Int) extends Pixel {

	@throws (classOf[ExceptionInInitializerError])
	val value : RgbValue = RgbValue(r, g, b)
}
