package models.pixel

case class RGB(r: Int, g: Int, b: Int) {
	private val _r = {
		if (0 to 255 contains _r) r
		else
			throw new IllegalArgumentException("Argument 'r' must be between 0 to 255")
	}

	private val _g = {
		if (0 to 255 contains _g) g
		else
			throw new IllegalArgumentException("Argument 'g' must be between 0 to 255")
	}

	private val _b = {
		if (0 to 255 contains _b) b
		else
			throw new IllegalArgumentException("Argument 'b' must be between 0 to 255")
	}
}

class PixelRGB(r: Int, g: Int, b: Int) extends PixelNumeric[RGB] {
	override def value: RGB = {
		try {
			RGB(r, g, b)
		} catch {
			case e: IllegalArgumentException => throw new IllegalArgumentException(e.getMessage)
		}
	}

	override def getGreyscale(): Int = {
		((0.3 * value.r) + (0.59 * value.g) + (0.11 * value.b)).toInt
	}
}
