package app.processor

import app.converters.{AsciiConverter, Converter, GreyScaler}
import app.filters.ImageFilter
import app.models.image.{Image, ImageAscii, ImageGrey, ImageRgb}
import app.models.pixel.Pixel
import filter.Filter

trait ImageProcessor {
	def loadImage(img: ImageRgb): Unit

	def greyScaleImage(greyScaleConverter: GreyScaler): Unit

	def filterImage(filter: ImageFilter): Unit

	def convertImage(converter: AsciiConverter): Unit

	/**
	 * I am not using this form of method, because variation does not work
	 */
//	def convertImage(conversion: Converter[Image[Pixel], Image[Pixel]]): Unit

	def getDoneImage: ImageAscii
}
