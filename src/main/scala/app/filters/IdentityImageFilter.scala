package app.filters

import app.models.image.ImageGrey
import filters.IdentityFilter

object IdentityImageFilter extends IdentityFilter[ImageGrey] with ImageFilter {
}
