package app.models.pixel

/**
 * Class holding data about RGB pixel
 * @param r Value of red pixel component
 * @param g Value of green component
 * @param b Value of blue component
 * @throws ExceptionInInitializerError If either of the parameters is smaller than 0 or greater than 255
 */
final case class PixelRgb(private val r: Int, private val g: Int, private val b: Int) extends Pixel {

	@throws (classOf[ExceptionInInitializerError])
	val value : RgbValue = RgbValue(r, g, b)
}
