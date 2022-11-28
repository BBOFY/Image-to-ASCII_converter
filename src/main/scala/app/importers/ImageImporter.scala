package app.importers
import app.models.image.Image
import importer._

trait ImageImporter[T<:Image[_]] extends Importer[Image[_]] {

}
