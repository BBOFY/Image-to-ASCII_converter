package app.filters.specific

import app.filters.ImageFilter
import app.models.image.{ImageGrey, ImageRegular, ImageRgb}
import app.models.pixel.{Pixel, PixelGrey, PixelNumeric, PixelRgb}

class BrightnessFilter(private val increment: Int) extends ImageFilter {
	/**
	 * Applies the specific filter on inserted image
	 *
	 * @param image on which filter will be applied
	 * @return item with applied filter
	 */
	/*override def apply(image: ImageRegular[_<:PixelNumeric[_]]): ImageRegular[_<:PixelNumeric[_]] = {

		val imgGrid: Vector[Vector[PixelNumeric[_]]] = image.getGrid

		imgGrid.map(row => {
			row.map( pixel => {
				pixel.incrementValue(increment)
			} )
		})

		image match {
			case _: ImageGrey => new ImageGrey(imgGrid)
			case _: ImageRgb => new ImageRgb(imgGrid)
		}

	}*/

	override def apply(image: ImageRegular[_ <: PixelNumeric[_]]): ImageRegular[_ <: PixelNumeric[_]] = {

		image match {
			case _: ImageGrey => {
				val imgGrid: Vector[Vector[PixelGrey]] = image.getGrid
				imgGrid.map(row => {
					row.map(pixel => {
						pixel.incrementValue(increment)
					})
				})
				new ImageGrey(imgGrid)
			}
			case _: ImageRgb => {
				val imgGrid: Vector[Vector[PixelRgb]] = image.getGrid
				imgGrid.map(row => {
					row.map(pixel => {
						pixel.incrementValue(increment)
					})
				})
				new ImageRgb(imgGrid)
			}
		}

	}
}
