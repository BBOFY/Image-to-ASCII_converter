package app.filters

import app.models.image.ImageGrey
import filter.Filter

trait ImageFilter extends Filter[ImageGrey] {
	override def equals(obj: Any): Boolean = super.equals()
}
