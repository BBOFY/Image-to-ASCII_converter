package filter

class IdentityFilter[T] extends Filter[T] {

	/**
	 * Returns the same item as inserted
	 * @param item Item on which filter will be applied
	 *  @return Item same as inserted item
	 */
	override def apply(item: T): T = item
}
