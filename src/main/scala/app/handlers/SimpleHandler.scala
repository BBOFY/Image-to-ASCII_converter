package app.handlers

class SimpleHandler[T] extends Handler[T] {
	var nextHandler: Option[Handler[T]] = None

	override def handle(item: T): Option[Handler[T]] = nextHandler

	override def setNext(next: Handler[T]): Handler[T] = {
		nextHandler = Some(next)
		next
	}
}

object SimpleHandler {
	def empty(): SimpleHandler[Nothing] = new SimpleHandler[Nothing]
}
