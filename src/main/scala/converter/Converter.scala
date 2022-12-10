package converter

trait Converter[-C, +P] {

	/**
	 * Converts an item
	 * @param item Item to convert
	 * @return Converted item
	 */
	def convert(item: C): P
}
