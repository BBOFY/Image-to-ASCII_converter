package filters.image.mixed

import dataTypes.Image
import filters.image.ImageFilter

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
