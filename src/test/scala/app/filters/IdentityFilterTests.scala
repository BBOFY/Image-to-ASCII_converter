package app.filters

import app.filters.specific.InvertFilter

class IdentityFilterTests extends FilterTests {
	protected val identityFilter: IdentityImageFilter.type = IdentityImageFilter

	test("Regular image 0") {
		val img = identityFilter.apply(_testImg0)
		assert(
			img.getGrid == _testImg0.getGrid
		)
	}

	test("Regular image 1") {
		val img = identityFilter.apply(_testImg1)
		assert(
			img.getGrid == _testImg1.getGrid
		)
	}

	test("Equality") {
		val otherIdentityFilter: ImageFilter = IdentityImageFilter
		assert(otherIdentityFilter == identityFilter)
	}

	test("Inequality") {
		val otherFilter: ImageFilter = new InvertFilter
		assert(otherFilter != identityFilter)
	}

}
