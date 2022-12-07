package app.filters.specific

import app.filters.ImageFilter

trait VariableFilter extends ImageFilter {
	def setValue(value: Int): Unit
}
