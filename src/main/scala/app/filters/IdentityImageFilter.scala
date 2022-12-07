package app.filters

import app.models.image.ImageGrey
import filter.IdentityFilter

object IdentityImageFilter extends IdentityFilter[ImageGrey] with ImageFilter {
}
