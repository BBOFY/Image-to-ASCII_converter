package app.models.utils

final case class RgbValue(_r: Int, _g: Int, _b: Int) {
	val r : Int = {
		if (0 to 255 contains _r) _r
		else
			throw new ExceptionInInitializerError("Argument 'r' must be between 0 to 255")
	}

	val g : Int = {
		if (0 to 255 contains _g) _g
		else
			throw new ExceptionInInitializerError("Argument 'g' must be between 0 to 255")
	}

	val b : Int = {
		if (0 to 255 contains _b) _b
		else
			throw new ExceptionInInitializerError("Argument 'b' must be between 0 to 255")
	}
}
