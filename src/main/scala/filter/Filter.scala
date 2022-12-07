package filter

trait Filter[T] {

	/**
	 * Applies the specific filter on inserted image
	 *
	 * @param item on which filter will be applied
	 * @return item with applied filter
	 */
	def apply(item: T): T
}
