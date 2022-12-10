package app.models.image

import app.models.pixel.PixelGrey

/**
 * Structure representing grey image
 * @param grid 2D vector with grey pixels
 */
class ImageGrey(private val grid: Vector[Vector[PixelGrey]]) extends Image[PixelGrey](grid) {

}
