package models.pixelOLD

trait Pixel[T] {
	def getValue: T

	def getGreyScale: Int
}
