package app.handlers.converterHandlers

import app.handlers.{Handler, SimpleHandler}

abstract class ConvertsionHandler extends SimpleHandler[String] {

	def handle(convTable: String): Option[Handler[String]]
}
