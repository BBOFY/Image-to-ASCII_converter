package app.filters

import app.models.image.{Image, ImageGrey}
import app.models.pixel.Pixel
import filters.Filter

trait ImageFilter extends Filter[ImageGrey] {

}
