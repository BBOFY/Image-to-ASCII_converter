package app.filters

import app.models.image.ImageGrey
import filter.IdentityFilter

object IdentityImageFilter extends IdentityFilter[ImageGrey] with ImageFilter {

	override def equals(obj: Any): Boolean = {
		obj match {
			case _: IdentityImageFilter.type => true
			case _ => false
		}
	}

}
