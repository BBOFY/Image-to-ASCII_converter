package handler

import scala.annotation.tailrec

trait Handler[T] {
	def handle(item: T): Option[Handler[T]]

	def setNext(next: Handler[T]): Handler[T]
}

object Handler {
	def handleAll[T](start: Handler[T], value: T): Unit = {

		@tailrec
		def handleLoop(current: Handler[T]): Unit = {
			current.handle(value) match {
				case Some(nextHandler) => handleLoop(nextHandler)
				case None =>
			}
		}
		handleLoop(start)
	}
}
