package app.inputParser

trait InputParser[T] {

	/**
	 * Checks validity of parsed input. If input is invalid, an exception is thrown, otherwise nothing happens
	 */
	def checkValidity(): Unit

	/**
	 * Remove desired number of arguments from parsing input
	 * @param count Number of arguments to remove
	 */
	def removeElements(count: Int): Unit

	/**
	 * Gets list of arguments in current parsing state
	 * @return List of currently parsing arguments
	 */
	def getArgs: List[T]

	/**
	 * @return True, if parsed input is empty, otherwise false
	 */
	def argsEmpty(): Boolean

}
