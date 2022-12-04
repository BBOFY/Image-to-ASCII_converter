package app.processor

import app.models.image.{Image, ImageGrey, ImageRgb}
import app.models.pixel.{Pixel, PixelGrey, PixelRgb}

object ImageProcessorImpl extends ImageProcessor {

	private var _image: Image[_<:Pixel] = new ImageGrey(Vector(Vector(PixelGrey(0))))

	override def loadImage(img: Image[_<:Pixel]): Unit = _image = img

	override def greyScaleImage(): Unit = {
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		for (row <- 0 until _image.height) {
			var newRow: Vector[PixelGrey] = Vector.empty
			for (col <- 0 until _image.width) {
				val greyScaleValue = _image.getPixel(col, row).getGreyScale
				newRow = newRow.appended(PixelGrey(greyScaleValue))
			}
			newGrid = newGrid.appended(newRow)
		}
		_image = new ImageGrey(newGrid)

	}

	override def getImage(): Image[_<:Pixel] = _image
}
