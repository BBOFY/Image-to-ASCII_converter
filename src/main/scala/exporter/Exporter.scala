package exporter

trait Exporter[-T] {

	/**
	 * Exports item to somewhere specified in child class
	 * @param item to export
	 */
	def export(item: T): Unit
}
