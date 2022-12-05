package app.converters

trait Converter[-C, +P] {
	def convert(item: C): P
}
