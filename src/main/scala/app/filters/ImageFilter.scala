package app.filters

import app.models.image.Image
import app.models.pixel.Pixel
import filters.Filter

trait ImageFilter extends Filter[Image[_<:Pixel]] {

}
