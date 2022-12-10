package filter

trait Filter[T] {

	/**
	 * Applies the specific filter on inserted item
	 *
	 * @param item Item on which filter will be applied
	 * @return Item with applied filter
	 */
	def apply(item: T): T
}
