package models.image

import models.pixel.Pixel

abstract class Image[T <: Pixel[_]](private val __width: Int, private val __height: Int ) {

	protected val _width: Int = {
		if (__width <= 0) throw new IllegalArgumentException("Width of the image must be greater than zero")
		__width
	}
	protected val _height: Int = {
		if (__height <= 0) throw new IllegalArgumentException("Height of the image must be greater than zero")
		__height
	}

	protected var _grid: Vector[Vector[T]]

	def width: Int = _width
	def height: Int = _height

	def getRow(y: Int): Vector[T] = {
		if (y >= _height) throwArgEx("y")
		_grid.apply(y)
	}

	def getColumn(x: Int): Vector[T] = {
		if (x >= _width) throwArgEx("x")
		var col = Vector[T]()
		for ( row <- _grid ) {
			col = col.appended( row.apply(x) )
		}
		col
	}

	def getPixel(x: Int, y: Int): T = {
		if (x >= _width) throwArgEx("x")
		if (y >= _height) throwArgEx("y")

		_grid.apply(y).apply(x)
	}

	def setRow(y: Int, newRow: Seq[T]): Unit  = {
		if (y >= height) throwArgEx("y")
		if (newRow.length != width) throw new IllegalArgumentException("Length of the new row must be equal to width")
		_grid = _grid.updated(y, newRow.toVector)
	}

	def setColumn(x: Int, newColumn: Seq[T]): Unit = {
		if (x >= width) throwArgEx("x")
		if (newColumn.length != height) throw new IllegalArgumentException("Length of the new column must be equal to height")

		for (row <- 0 until height) {
			_grid = _grid.updated(row, _grid.apply(row).updated(x, newColumn.apply(row)))
		}

		var col = Vector[T]()
		for (row <- _grid) {
			col = col.appended(row.apply(x))
		}
		col
	}

	def setPixel(x: Int, y: Int, newPixel: T): Unit = {
		if (x >= width) throwArgEx("x")
		if (y >= height) throwArgEx("y")
		_grid = _grid.updated(y, _grid.apply(y).updated(x, newPixel))
	}

	protected def throwArgEx(param: String): Unit = {
		throw new IllegalArgumentException(s"Parameter $param is out of bounds of image size")
	}

}
