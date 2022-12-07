package app.inputParser

trait InputParser[T] {
	def checkValidity: Boolean

	def removeElements(count: Int): List[T]

	def getFirstElement: T

}
