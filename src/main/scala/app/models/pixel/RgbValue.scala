package app.models.pixel

final case class RgbValue(private val _r: Int, private val _g: Int, private val _b: Int) {
	val r: Int = {
		if (0 to 255 contains _r) _r
		else
			throw new ExceptionInInitializerError("Argument 'r' must be between 0 to 255")
	}

	val g: Int = {
		if (0 to 255 contains _g) _g
		else
			throw new ExceptionInInitializerError("Argument 'g' must be between 0 to 255")
	}

	val b: Int = {
		if (0 to 255 contains _b) _b
		else
			throw new ExceptionInInitializerError("Argument 'b' must be between 0 to 255")
	}
}
