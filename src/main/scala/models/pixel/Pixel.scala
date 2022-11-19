package models.pixel

trait Pixel[T] {
	def getValue: T

	def getGreyScale: Int
}
