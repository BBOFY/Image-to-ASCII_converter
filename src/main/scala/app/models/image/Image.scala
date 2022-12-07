package app.models.image

import app.models.pixel.Pixel

abstract class Image[+T](private val __grid: Vector[Vector[T]]) {

	protected val _grid: Vector[Vector[T]] = {
		if (__grid.isEmpty) throw new IllegalArgumentException("Image must have at least 1x1 dimensions")

		val w = __grid.apply(0).length

		__grid.foreach( row => {
			if (row.isEmpty) throw new IllegalArgumentException("Rows must not be empty")
			if (row.length != w) throw new IllegalArgumentException("Image must be rectangular")
		} )
		__grid
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

	def getPixel(x: Int, y: Int): T = {
		if (x >= _width) throwArgEx("x")
		if (y >= _height) throwArgEx("y")
		_grid.apply(y).apply(x)
	}

	protected def throwArgEx(param: String): Unit = {
		throw new IllegalArgumentException(s"Parameter $param is out of bounds of image size")
	}

	override def equals(o: Any): Boolean = o match {
		case that: Image[_] => that.getGrid == this.getGrid
		case _ => false
	}

	override def hashCode: Int = __grid.hashCode
}



