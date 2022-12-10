package app.models.image

import app.models.pixel.PixelAscii

/**
 * Structure representing ascii image
 * @param grid 2D vector with ascii pixels
 */
class ImageAscii(private val grid: Vector[Vector[PixelAscii]]) extends Image[PixelAscii](grid) {
}
