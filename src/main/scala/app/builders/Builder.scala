package app.builders

trait Builder[-C, +P] {
	def registerProperty(item: C): Unit
	def build: P
}
