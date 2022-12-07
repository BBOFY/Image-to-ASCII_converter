package app.filters

trait VariableFilter extends ImageFilter {
	def setValue(value: Int): Unit
}
