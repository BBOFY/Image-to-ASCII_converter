package app.builders

import app.filters.{IdentityImageFilter, ImageFilter}
import app.filters.mixed.MixedFilter

class FilterBuilder {

	var filters: Seq[ImageFilter] = Seq(IdentityImageFilter)

	def registerProperty(filter: ImageFilter): Unit = {
		filters = filters.appended(filter)
	}

	def build: ImageFilter = new MixedFilter(filters)

}
