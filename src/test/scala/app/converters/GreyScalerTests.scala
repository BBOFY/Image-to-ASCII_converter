//package app.converters
//
//import app.models.image.ImageGrey
//import app.models.pixel.PixelGrey
//import org.scalatest.FunSuite
//
//class GreyScalerTests extends FunSuite {
//
//	test("Grey to grey") {
//
//		val grid: Vector[Vector[PixelGrey]] = Vector (
//		  Vector(PixelGrey(10), PixelGrey(20), PixelGrey(30)),
//		  Vector(PixelGrey(40), PixelGrey(50), PixelGrey(60)),
//		  Vector(PixelGrey(70), PixelGrey(80), PixelGrey(90))
//		)
//
//		val testImage: ImageGrey = new ImageGrey(grid)
//
//		val newImage = GreyScaler.convert(testImage)
//
//		for (x <- 0 until 3) {
//			for (y <- 0 until 3) {
//				assert(
//					newImage.getPixel(x, y).value == testImage.getPixel(x, y).value_
//				)
//			}
//		}
//
//	}
//
//}
