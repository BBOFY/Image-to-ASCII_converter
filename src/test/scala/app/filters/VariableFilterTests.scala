package app.filters

import app.models.image.ImageGrey
import org.scalatest.FunSuite

abstract class VariableFilterTests extends FunSuite {
	protected def validFilterTest(name: String,
								  filter: VariableFilter,
								  orgImg: ImageGrey,
								  refImg: ImageGrey,
								  value: Int
								 ): Unit = {
		test(name) {
			filter.setValue(value)
			val img = filter.apply(orgImg)
			assert(img == refImg)
		}
	}
}
