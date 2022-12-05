package app.inputParser

trait InputParser[T] {

	def getImageSource: T

	def getFilterArgs: T

	def getConversionArgs: T

	def getExportingArgs: T

}
