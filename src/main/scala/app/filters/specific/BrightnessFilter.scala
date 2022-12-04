package app.filters.specific

import app.filters.ImageFilter
import app.models.image.{Image, ImageGrey}
import app.models.pixel.{Pixel, PixelGrey}


class BrightnessFilter(private val increment: Int) extends ImageFilter {

	override def apply(image: ImageGrey): ImageGrey = {

		val imgGrid = image.getGrid

		imgGrid.map(row => {
			row.map( pixel => {
				pixel
			} )
		})

		image


	}

//	def foo(): Image[Pixel] = {
//
//		val testingGrid: Vector[Vector[PixelGrey]] = Vector(
//			Vector(PixelGrey(0), PixelGrey(1), PixelGrey(2)),
//			Vector(PixelGrey(3), PixelGrey(4), PixelGrey(5))
//		)
//		new ImageGrey(testingGrid)
//
//	}

}

//Type mismatch.
//Required: Vector[Vector[Pixel]]
//   found: Vector[Vector[PixelGrey]]

