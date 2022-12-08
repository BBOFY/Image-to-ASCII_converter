package app.handlers

import handler.{Handler, SimpleHandler}

trait CommandHandler extends SimpleHandler[List[String]] {
	def handle(args: List[String]): Option[Handler[List[String]]]
}
