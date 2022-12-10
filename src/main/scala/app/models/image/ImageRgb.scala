package app.models.image

import app.models.pixel.PixelRgb

/**
 * Structure representing RGB image
 * @param grid 2D vector with RGB pixels
 */
class ImageRgb(private val grid: Vector[Vector[PixelRgb]]) extends Image[PixelRgb](grid) {

}
