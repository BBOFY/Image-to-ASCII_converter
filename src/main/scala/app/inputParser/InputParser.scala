package app.inputParser

trait InputParser[T] {
	def checkValidity(): Unit

	def removeElements(count: Int): Unit

	def getArgs: List[T]

	def argsEmpty(): Boolean

}
