package app.builders

import app.filters.{IdentityImageFilter, ImageFilter}
import app.filters.mixed.MixedFilter

class FilterBuilder extends Builder[ImageFilter, ImageFilter] {

	protected var _filters: Seq[ImageFilter] = Seq.empty

	/**
	 * Saves given filter into sequence of filters, which will be later used to build mixed filter
	 * @param filter Filter to be saved to sequence
	 */
	override def registerProperty(filter: ImageFilter): Unit = {
		_filters = _filters.appended(filter)
	}

	/**
	 *  @return Mixed filter containing previously registered filters, or mixed filter containing identity filter if no filter was registerd
	 */
	override def build: ImageFilter = {
		if (_filters.isEmpty)
			_filters = Seq(IdentityImageFilter)
		new MixedFilter(_filters)
	}

}
