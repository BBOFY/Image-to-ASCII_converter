package app.processor

import app.converters.{AsciiConverter, GreyScaler, TextConverter}
import app.filters.ImageFilter
import app.models.image.ImageRgb
import exporter.text.TextExporter

trait ImageProcessor {
	def loadImage(img: ImageRgb): Unit

	def activatePipeline(filter: ImageFilter,
						 asciiConverter: AsciiConverter,
						 exporter: TextExporter,
						 greyScaler: GreyScaler,
						 textConverter: TextConverter
						): Unit
}
