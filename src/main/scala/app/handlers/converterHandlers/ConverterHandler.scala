package app.handlers.converterHandlers

import handler.{Handler, SimpleHandler}

trait ConverterHandler extends SimpleHandler[List[String]] {

	def handle(args: List[String]): Option[Handler[List[String]]]
}
