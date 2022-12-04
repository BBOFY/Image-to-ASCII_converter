//package app.models.pixel
//
//abstract class PixelNumeric[PixelVal] extends Pixel {
//
//	def value: T
//	def getGreyscale: Int
//
//	def incrementValue(inc: Int): Unit
//
//	protected def sumInInterval(a: Int, b: Int, minimum: Int, maximum: Int): Int = {
//		val result = a + b
//
//		if (result >= maximum) maximum
//		else if (result <= minimum) minimum
//		else result
//	}
//
//}
