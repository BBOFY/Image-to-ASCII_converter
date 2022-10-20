package filters.defualt

import filters.Filter

class IdentityFilter[T] extends Filter[T] {

	override def apply(item: T): T = item
}
