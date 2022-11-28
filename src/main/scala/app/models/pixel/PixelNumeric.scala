package app.models.pixel

abstract class PixelNumeric[T] extends Pixel {

	def value: T
	def getGreyscale: Int

}
