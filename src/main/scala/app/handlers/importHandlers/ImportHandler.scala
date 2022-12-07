package app.handlers.importHandlers

import app.handlers.{Handler, SimpleHandler}

trait ImportHandler extends SimpleHandler[List[String]] {

	def handle(args: List[String]): Option[Handler[List[String]]]
}
