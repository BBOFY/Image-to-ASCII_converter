package app.filters.mixed

import app.filters.{IdentityImageFilter, ImageFilter}
import app.models.image.ImageGrey

class MixedFilter (val filters: Seq[ImageFilter] = Seq(IdentityImageFilter)) extends ImageFilter {

	protected val _filters: Seq[ImageFilter] = filters

	override def apply(item: ImageGrey): ImageGrey = {
		var image = item
		_filters.foreach(filter => {
			image = filter.apply(image)
		})
		image
	}

	override def equals(obj: Any): Boolean = {
		obj match {
			case that: MixedFilter => that._filters == this._filters
			case _ => false
		}
	}
}
