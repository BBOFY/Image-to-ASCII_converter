package app.processor

import app.converters.{AsciiConverter, Converter, GreyScaler}
import app.filters.ImageFilter
import app.models.image.{Image, ImageAscii, ImageGrey, ImageRgb}
import app.models.pixel.{Pixel, PixelAscii, PixelGrey, PixelRgb}

class ImageProcessorImpl extends ImageProcessor {

	private var importedImage = new ImageRgb(Vector(Vector(PixelRgb(0, 0, 0))))
	private var greyedImage = new ImageGrey(Vector(Vector(PixelGrey(0))))
	private var asciiImage = new ImageAscii(Vector(Vector(PixelAscii('F'))))

	override def loadImage(img: ImageRgb): Unit = importedImage = img


	override def greyScaleImage(greyScaleConverter: GreyScaler): Unit = {
		greyedImage = greyScaleConverter.convert(importedImage)
	}

	override def getDoneImage: ImageAscii = asciiImage


	override def convertImage(converter: AsciiConverter): Unit = {
		asciiImage = converter.convert(greyedImage)
	}

	override def filterImage(filter: ImageFilter): Unit = {
		greyedImage = filter.apply(greyedImage)
	}
}
