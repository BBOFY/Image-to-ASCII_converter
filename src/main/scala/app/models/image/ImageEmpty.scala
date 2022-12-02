package app.models.image

import app.models.pixel.PixelEmpty

object ImageEmpty extends Image[PixelEmpty](Vector.empty) {
	override protected val _width: Int = 0
	override protected val _height: Int = 0

	override def width: Int = super.width

	override def height: Int = super.height

//	override def getRow(y: Int): Vector[Nothing] = Vector.empty

//	override def getColumn(x: Int): Vector[Nothing] = Vector.empty

	override def getPixel(x: Int, y: Int): PixelEmpty = new PixelEmpty

//	override def setRow(y: Int, newRow: Seq[Nothing]): Unit = throw new NotImplementedError("Cannot set for empty image")

//	override def setColumn(x: Int, newColumn: Seq[Nothing]): Unit = throw new NotImplementedError("Cannot set for empty image")

	def setPixel(x: Int, y: Int, newPixel: Nothing): Unit = throw new NotImplementedError("Cannot set for empty image")

	override protected def throwArgEx(param: String): Unit = super.throwArgEx(param)
}
