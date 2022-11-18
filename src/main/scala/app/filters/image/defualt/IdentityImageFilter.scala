package filters.image.defualt

import filters.defualt.IdentityFilter
import filters.image.ImageFilter
import models.image.Image

object IdentityImageFilter extends IdentityFilter[Image] with ImageFilter {
}
