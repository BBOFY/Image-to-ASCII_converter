package models.pixel

import models.utils.pixelValue.PixelValue

trait Pixel {

	def getGreyScale: Int
	def getValue: PixelValue
}
