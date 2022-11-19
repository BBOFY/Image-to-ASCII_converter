package models.image

import models.pixel.PixelRGB

class ImageRGB extends Image[PixelRGB] {
	override def getRow(y: Int): List[PixelRGB] = ???

	override def getColumn(x: Int): List[PixelRGB] = ???

	override def getPixel(x: Int, y: Int): PixelRGB = ???

}
