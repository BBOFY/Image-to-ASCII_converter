package converter

trait Converter[T, S] {
	def convert(item: T): S
}
