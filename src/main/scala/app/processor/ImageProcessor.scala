package app.processor

import app.converters.{AsciiConverter, GreyScaler, TextConverter}
import app.filters.ImageFilter
import app.models.image.ImageRgb
import exporter.text.TextExporter

/**
 * Specifies API for worker class for holding an imported image and applying desired modifications on it
 */
trait ImageProcessor {

	/**
	 * Stores an imported image into as internal value
	 * @param img Image to store
	 */
	def loadImage(img: ImageRgb): Unit

	/**
	 * Activates an application of defined modifications on the stored image
	 * @param filter Filter to apply onto the image
	 * @param asciiConverter Converter to convert image from ImageGrey to ImageAscii
	 * @param exporters Sequence of exporters to export an image to various destinations
	 * @param greyScaler Converter to convert image from ImageRgb to ImageGrey
	 * @param textConverter Converter to convert image from ImageAscii to String as plain text
	 */
	def activatePipeline(filter: ImageFilter,
						 asciiConverter: AsciiConverter,
						 exporters: Seq[TextExporter],
						 greyScaler: GreyScaler,
						 textConverter: TextConverter
						): Unit
}