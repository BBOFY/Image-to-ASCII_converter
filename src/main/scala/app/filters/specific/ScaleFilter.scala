//package app.filters.specific
//
//import app.filters.ImageFilter
//import app.models.image.ImageGrey
//import app.models.pixel.PixelGrey
//
//class ScaleFilter(val scaleValue: Double) extends ImageFilter {
//	override def apply(image: ImageGrey): ImageGrey = {
//
//		val grid = image.getGrid
//		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty
//
//		scaleValue match {
//			case 0.25 =>
//				(image.width/2.0).toInt
//
//			case 1 => image
//
//			case 4 =>
//				for (row <- grid) {
//					var newRow: Vector[PixelGrey] = Vector.empty
//					for (pixel <- row) newRow = newRow.appended(pixel).appended(pixel)
//					newGrid = newGrid.appended(newRow).appended(newRow)
//				}
//				new ImageGrey(newGrid)
//
//		}
//
//	}
//}
