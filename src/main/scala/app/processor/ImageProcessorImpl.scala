package app.processor

import app.converters.{AsciiConverter, GreyScaler, TextConverter}
import app.filters.ImageFilter
import app.models.image.ImageRgb
import app.models.pixel.PixelRgb
import exporter.text.TextExporter

class ImageProcessorImpl extends ImageProcessor {

	protected var _importedImage: ImageRgb = new ImageRgb(Vector(Vector(PixelRgb(0, 0, 0))))

	override def loadImage(img: ImageRgb): Unit = _importedImage = img

	def activatePipeline(filter: ImageFilter,
						 asciiConverter: AsciiConverter,
						 exporters: Seq[TextExporter],
						 greyScaler: GreyScaler = new GreyScaler,
						 textConverter: TextConverter = new TextConverter,
	): Unit = {
		val imageInText = textConverter.convert(
			asciiConverter.convert(
				filter.apply(
					greyScaler.convert(
						_importedImage
					)
				)
			)
		)

		for (exporter <- exporters) {
			exporter.`export`(imageInText)
		}
	}
}
