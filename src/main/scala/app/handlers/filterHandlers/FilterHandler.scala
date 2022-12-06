package app.handlers.filterHandlers

import app.handlers.{Handler, SimpleHandler}

trait FilterHandler extends SimpleHandler[List[String]] {
	def handle(args: List[String]): Option[Handler[List[String]]]
}
