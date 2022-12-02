package app.processor

import app.models.image.Image
import filters.Filter
import jdk.incubator.vector.VectorOperators.Conversion

trait ImageProcessor {
	def loadImage(img: Image[_]): Unit

	def greyScaleImage(): Unit

//	def applyFilter(filter: Filter): Unit

//	def convertImage(conversion: Conversion): Unit

	def getImage(): Image[_]
}
