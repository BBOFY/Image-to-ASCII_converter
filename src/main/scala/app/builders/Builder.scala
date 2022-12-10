package app.builders

trait Builder[-C, +P] {

	/**
	 * Registers some property that builder would use
	 * @param item Property to register
	 */
	def registerProperty(item: C): Unit

	/**
	 * Builds new object based on previously given properties
	 * @return New object with given properties
	 */
	def build: P
}
