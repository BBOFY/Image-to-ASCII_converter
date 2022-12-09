package app.builders

import app.filters.{IdentityImageFilter, ImageFilter}
import app.filters.mixed.MixedFilter

class FilterBuilder extends Builder[ImageFilter, ImageFilter] {

	private var filters: Seq[ImageFilter] = Seq.empty

	override def registerProperty(filter: ImageFilter): Unit = {
		filters = filters.appended(filter)
	}

	override def build: ImageFilter = {
		if (filters.isEmpty)
			filters = Seq(IdentityImageFilter)
		new MixedFilter(filters)
	}

}
