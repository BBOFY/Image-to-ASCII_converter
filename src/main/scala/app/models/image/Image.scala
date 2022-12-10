package app.models.image

/**
 * Structure representing image with 2D vector containing individual representations of pixels
 * @param grid 2D vector with defined type of pixels
 * @tparam T Type of pixel to contain
 * @throws ExceptionInInitializerError If grid is empty,
 */
abstract class Image[+T](private val grid: Vector[Vector[T]]) {

	protected val _grid: Vector[Vector[T]] = {
		if (grid.isEmpty) throw new ExceptionInInitializerError("Image must have at least 1x1 dimensions")

		val w = grid.apply(0).length

		grid.foreach( row => {
			if (row.isEmpty) throw new ExceptionInInitializerError("Rows must not be empty")
			if (row.length != w) throw new ExceptionInInitializerError("Image must be rectangular")
		} )
		grid
	}

	protected val _width: Int = {
		_grid.apply(0).length
	}
	protected val _height: Int = {
		_grid.length
	}

	def getGrid: Vector[Vector[T]] = _grid

	def width: Int = _width
	def height: Int = _height

	/**
	 * @throws IndexOutOfBoundsException If x ot y parameter is outside of image dimensions
	 */
	def getPixel(x: Int, y: Int): T = {
		if (x >= _width) throw new IndexOutOfBoundsException("Parameter \'x\' is out of bounds of image size")
		if (y >= _height) throw new IndexOutOfBoundsException("Parameter \'y\' is out of bounds of image size")
		_grid.apply(y).apply(x)
	}
}



