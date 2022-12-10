package app.filters.mixed

import app.filters.{IdentityImageFilter, ImageFilter}
import app.models.image.ImageGrey

/**
 * Filter-like structure managing multiple filters.
 * @param filters Sequence of filter, which would be applied later. Default value is sequence only with identity filter
 */
class MixedFilter (val filters: Seq[ImageFilter] = Seq(IdentityImageFilter)) extends ImageFilter {

	protected val _filters: Seq[ImageFilter] = filters

	/**
	 * 	Applies effect of all filters on given image
	 *  @param item Image on which filters will be applied
	 *  @return Image with applied filters
	 */
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
