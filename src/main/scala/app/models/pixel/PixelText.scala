package app.models.pixel

abstract class PixelText[T] extends Pixel {
	def value: T
}
