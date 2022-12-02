package app.processor

import app.models.image.{Image, ImageGrey, ImageRgb}
import app.models.pixel.{PixelGrey, PixelRgb}

object ImageProcessorImpl extends ImageProcessor {

	private var _image: Image[_] = new ImageGrey(Vector(Vector(PixelGrey(0))))

	override def loadImage(img: Image[_]): Unit = _image = img

	override def greyScaleImage(): Unit = {}

	override def getImage(): Image[_] = _image
}
