package importing

trait Importer[T] {
	def doImport(): T
}
