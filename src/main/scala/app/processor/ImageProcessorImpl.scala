package app.processor

import app.models.image.{Image, ImageEmpty}

object ImageProcessorImpl extends ImageProcessor {

	private var _image: Image[_] = ImageEmpty

	override def loadImage(img: Image[_]): Unit = _image = img

	override def greyScaleImage(): Unit = {}

	override def getImage(): Image[_] = _image
}
