package converter

trait Converter[T, S] {

	/**
	 * Converts an item
	 * @param item Item to convert
	 * @return Converted item
	 */
	def convert(item: T): S
}
