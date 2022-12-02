package app.filters.mixed

import app.filters.ImageFilter
import app.models.image.Image
import app.models.pixel.Pixel

class MixedFilter (filters: Seq[ImageFilter]) extends ImageFilter {
	/**
	 * Iterates over given filters and applies them on given item
	 *
	 * @param item on which filter will be applied
	 * @return item with applied filter
	 */
	override def apply(item: Image[_<:Pixel]): Image[_<:Pixel] = {
//		filters.foldLeft(item)((accumulator, filter) => {
//			filter.apply(accumulator)
//		})

		var image = item
		filters.foreach(filter => {
			image = filter.apply(image)
		})

		image
	}
}
