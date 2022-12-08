package app.filters.mixed

import app.filters.{IdentityImageFilter, ImageFilter}
import app.models.image.ImageGrey

class MixedFilter (filters: Seq[ImageFilter] = Seq(IdentityImageFilter)) extends ImageFilter {
	/**
	 * Iterates over given filters and applies them on given item
	 *
	 * @param item on which filter will be applied
	 * @return item with applied filter
	 */
	override def apply(item: ImageGrey): ImageGrey = {
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
