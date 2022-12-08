package app.filters

import app.models.image.ImageGrey

abstract class VariableFilterTests extends FilterTests {
	protected def validFilterTest(name: String,
								  filter: VariableFilter,
								  orgImg: ImageGrey,
								  refImg: ImageGrey,
								  value: Int
								 ): Unit = {
		test(name) {
			filter.setValue(value)
			val img = filter.apply(orgImg)
			assert(img.getGrid == refImg.getGrid)
		}
	}
}
