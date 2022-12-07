package filter

class IdentityFilter[T] extends Filter[T] {

	override def apply(item: T): T = item
}
