package app.processor

import app.converters.{AsciiLinearConverter, Converter}
import app.models.image.{Image, ImageAscii, ImageGrey}
import app.models.pixel.Pixel
import filters.Filter

trait ImageProcessor {
	def loadImage(img: Image[_<:Pixel]): Unit

	def greyScaleImage(): Unit

//	def applyFilter(filter: Filter): Unit

//	def convertImage(conversion: Converter[ImageGrey, ImageAscii]): Unit

	def convertImage(conversionTable: String): Unit

	def getImage: Image[_<:Pixel]
}
