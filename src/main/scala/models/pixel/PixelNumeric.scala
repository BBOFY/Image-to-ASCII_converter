package models.pixel

trait PixelNumeric[T] extends Pixel {

	def value: T

	def getGreyscale(): Int

}
