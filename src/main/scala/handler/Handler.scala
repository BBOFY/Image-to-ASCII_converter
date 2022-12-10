package handler

import scala.annotation.tailrec

trait Handler[T] {

	/**
	 * Handles item and may return another handler handling the same item
	 * @param item Item to handle
	 * @return The next handler or none. Exact behaviour is defined in child classes
	 */
	def handle(item: T): Option[Handler[T]]

	/**
	 * Sets the next handler for caller
	 * @param next Handler to be the next
	 * @return Next handler
	 */
	def setNext(next: Handler[T]): Handler[T]
}

object Handler {

	/**
	 * Resolves all handlers in handler chain following the initial handler
	 * @param start Initial handler
	 * @param value Value to be handled by handler chain
	 * @tparam T Type of handler chain
	 */
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
