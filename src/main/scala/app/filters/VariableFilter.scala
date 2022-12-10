package app.filters

trait VariableFilter extends ImageFilter {

	/**
	 * Sets numeric value the filter would use for altering the iamge
	 * @param value Value for filter to work with
	 */
	def setValue(value: Int): Unit
}
