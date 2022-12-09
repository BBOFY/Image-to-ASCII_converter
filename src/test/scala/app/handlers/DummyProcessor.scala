package app.handlers

import app.models.image.ImageRgb
import app.processor.ImageProcessorImpl

class DummyProcessor extends ImageProcessorImpl {
	var image: ImageRgb = _importedImage
	var counter = 0

	override def loadImage(img: ImageRgb): Unit = {
		counter = counter + 1
		image = img
	}
}
