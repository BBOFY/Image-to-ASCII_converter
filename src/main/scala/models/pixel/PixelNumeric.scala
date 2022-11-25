package models.pixel

abstract class PixelNumeric[T] extends Pixel[T] {

	def getGreyscale: Int

}
