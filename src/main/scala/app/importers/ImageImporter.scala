package app.importers
import app.models.image.Image
import importing._

trait ImageImporter[T<:Image[_]] extends Importer[Image[_]] {

}
