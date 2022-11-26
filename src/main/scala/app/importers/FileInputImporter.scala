package app.importers

import app.models.image.Image

abstract class FileInputImporter[T<:Image[_]](private val path: String) extends ImageImporter[T] {

}
