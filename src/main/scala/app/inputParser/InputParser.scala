package app.inputParser

trait InputParser[T] {
	def checkValidity(): Unit

	def removeElements(count: Int): List[T]

	def getArgs: List[T]

	def argsStatus(): String

}
