package filters.image.mixed

import filters.image.ImageFilter
import models.dataTypes.Image

class MixedFilter (filters: Seq[ImageFilter]) extends ImageFilter {
	/**
	 * Iterates over given filters and applies them on given item
	 *
	 * @param item on which filter will be applied
	 * @return item with applied filter
	 */
	override def apply(item: Image): Image = {
		filters.foldLeft(item)((accumulator, filter) => filter.apply(accumulator))
	}
}
