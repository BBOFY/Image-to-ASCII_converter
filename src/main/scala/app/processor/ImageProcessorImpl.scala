package app.processor

import app.converters.{AsciiConverter, GreyScaler, TextConverter}
import app.filters.ImageFilter
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb
import exporter.text.TextExporter

class ImageProcessorImpl extends ImageProcessor {

	private var importedImage: ImageRgb = new ImageRgb(Vector(Vector(PixelRgb(0, 0, 0))))

	override def loadImage(img: ImageRgb): Unit = importedImage = img

	def activatePipeline(filter: ImageFilter,
						 asciiConverter: AsciiConverter,
						 exporter: TextExporter,
						 greyScaler: GreyScaler = new GreyScaler,
						 textConverter: TextConverter = new TextConverter,
	): Unit = {
		exporter.`export`(
		textConverter.convert(
				asciiConverter.convert(
					filter.apply(
						greyScaler.convert(
							importedImage
						)
					)
				)
			)
		)
	}


}
