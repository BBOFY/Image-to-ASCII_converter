package models.pixel

case class Rgb(_r: Int, _g: Int, _b: Int) {
	private val r_ : Int = {
		if (0 to 255 contains _r) _r
		else
			throw new IllegalArgumentException("Argument 'r' must be between 0 to 255")
	}

	private val g_ : Int = {
		if (0 to 255 contains _g) _g
		else
			throw new IllegalArgumentException("Argument 'g' must be between 0 to 255")
	}

	private val b_ : Int = {
		if (0 to 255 contains _b) _b
		else
			throw new IllegalArgumentException("Argument 'b' must be between 0 to 255")
	}

	def r: Int = r_
	def g: Int = g_
	def b: Int = b_
}
