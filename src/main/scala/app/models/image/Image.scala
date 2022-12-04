package app.models.image

import app.models.pixel.Pixel

abstract class Image[T<:Pixel](private val __grid: Vector[Vector[T]]) {

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

//	def getRow(y: Int): Vector[T] = {
//		if (y >= _height) throwArgEx("y")
//		_grid.apply(y)
//	}
//
//	def getColumn(x: Int): Vector[T] = {
//		if (x >= _width) throwArgEx("x")
//		var col = Vector[T]()
//		_grid.foreach(row => {
//			col = col.appended(row.apply(x))
//		})
//		col
//	}

	def getPixel(x: Int, y: Int): T = {
		if (x >= _width) throwArgEx("x")
		if (y >= _height) throwArgEx("y")
		_grid.apply(y).apply(x)
	}

//	def setRow(y: Int, newRow: Seq[T]): Unit  = {
//		if (y >= height) throwArgEx("y")
//		if (newRow.length != width) throw new IllegalArgumentException("Length of the new row must be equal to width")
//		_grid = _grid.updated(y, newRow.toVector)
//	}
//
//	def setColumn(x: Int, newColumn: Seq[T]): Unit = {
//		if (x >= width) throwArgEx("x")
//		if (newColumn.length != height) throw new IllegalArgumentException("Length of the new column must be equal to height")
//
//		for (row <- 0 until height) {
//			_grid = _grid.updated(row, _grid.apply(row).updated(x, newColumn.apply(row)))
//		}
//	}

//	def setPixel(x: Int, y: Int, newPixel: T): Unit = {
//		if (x >= width) throwArgEx("x")
//		if (y >= height) throwArgEx("y")
//		_grid = _grid.updated(y, _grid.apply(y).updated(x, newPixel))
//	}

	protected def throwArgEx(param: String): Unit = {
		throw new IllegalArgumentException(s"Parameter $param is out of bounds of image size")
	}

}
