package filters.image.defualt

import dataTypes.Image
import filters.defualt.IdentityFilter
import filters.image.ImageFilter

object IdentityImageFilter extends IdentityFilter[Image] with ImageFilter {
}
