package app.importers

import app.models.image.Image

abstract class FileInputImporter[T<:Image[_]] extends ImageImporter[T] {

}
