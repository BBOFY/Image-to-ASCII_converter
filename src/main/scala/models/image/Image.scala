//package models.image
//
//import models.pixelOLD.Pixel
//import models.utils.Pos
//
//import java.security.InvalidParameterException
//
//trait Image[T] {
//
//	protected val _grid: Option[List[List[T]]]
//	protected val _width: Int
//	protected val _height: Int
//
//	def width: Int = _width
//	def height: Int = _height
//
//	def getRow(y: Int): List[T] = {
//		if (y >= _height) throw new InvalidParameterException("Parameter y must be smaller than height of an image")
//		_grid.get.apply(y)
//	}
//
//	def getColumn(x: Int): List[T] = {
//		if (x >= _width) throw new InvalidParameterException("Parameter x must be smaller than width of an image")
//		var col = List[T]()
//		for ( row <- _grid.get ) {
//			col = col.appended( row.apply(x) )
//		}
//		col
//	}
//
//	def getPixel(x: Int, y: Int): T = {
//		if (x >= _width) throw new InvalidParameterException("Parameter x must be smaller than width of an image")
//		if (y >= _height) throw new InvalidParameterException("Parameter y must be smaller than height of an image")
//
//		_grid.get.apply(y).apply(x)
//	}
//
//	def setRow(y: Int, ): Image[T] = {
//		if (y >= height) throw new InvalidParameterException("Parameter y must be smaller than height of an image")
//		_grid.get.apply(y)
//	}
//
//	def setColumn(x: Int): List[T] = {
//		if (x >= width) throw new InvalidParameterException("Parameter x must be smaller than width of an image")
//		var col = List[T]()
//		for (row <- grid.get) {
//			col = col.appended(row.apply(x))
//		}
//		col
//	}
//
//	def setPixel(x: Int, y: Int): T = {
//		if (x >= width) throw new InvalidParameterException("Parameter x must be smaller than width of an image")
//		if (y >= height) throw new InvalidParameterException("Parameter y must be smaller than height of an image")
//
//		grid.get.apply(y).apply(x)
//	}
//
//
//}
