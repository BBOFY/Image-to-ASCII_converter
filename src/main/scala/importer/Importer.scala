package importer

trait Importer[T] {

	/**
	 * Imports something from somewhere
	 * @return imported thing
	 */
	def doImport(): T
}
