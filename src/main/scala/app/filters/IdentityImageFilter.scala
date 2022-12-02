package app.filters

import app.models.image.Image
import app.models.pixel.Pixel
import filters.IdentityFilter

object IdentityImageFilter extends IdentityFilter[Image[_<:Pixel]] with ImageFilter {
}
