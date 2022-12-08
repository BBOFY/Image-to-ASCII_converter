package exporter

trait Exporter[-T] {
	def export(item: T): Unit
}
