package app.models.image

import app.models.pixel.PixelGrey

class ImageGrey(private val grid: Vector[Vector[PixelGrey]]) extends Image[PixelGrey](grid) {}
