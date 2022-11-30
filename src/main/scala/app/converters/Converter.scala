package app.converters

trait Converter[-T, +S] {
	def convert(item: T): S
}
