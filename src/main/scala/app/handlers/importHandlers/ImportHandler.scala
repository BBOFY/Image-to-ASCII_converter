package app.handlers.importHandlers

import handler.{Handler, SimpleHandler}

trait ImportHandler extends SimpleHandler[List[String]] {

	def handle(args: List[String]): Option[Handler[List[String]]]
}
