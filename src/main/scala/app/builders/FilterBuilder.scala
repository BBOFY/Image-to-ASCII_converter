package app.builders

import app.filters.{IdentityImageFilter, ImageFilter}
import app.filters.mixed.MixedFilter

class FilterBuilder extends Builder[ImageFilter, ImageFilter] {

	protected var _filters: Seq[ImageFilter] = Seq.empty

	override def registerProperty(filter: ImageFilter): Unit = {
		_filters = _filters.appended(filter)
	}

	override def build: ImageFilter = {
		if (_filters.isEmpty)
			_filters = Seq(IdentityImageFilter)
		new MixedFilter(_filters)
	}

}
