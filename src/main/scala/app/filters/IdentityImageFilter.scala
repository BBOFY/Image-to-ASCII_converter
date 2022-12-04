package app.filters

import app.models.image.{Image, ImageGrey}
import app.models.pixel.Pixel
import filters.IdentityFilter

object IdentityImageFilter extends IdentityFilter[ImageGrey] with ImageFilter {
}
