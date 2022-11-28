package importer

trait Importer[T] {
	def doImport(): T
}
