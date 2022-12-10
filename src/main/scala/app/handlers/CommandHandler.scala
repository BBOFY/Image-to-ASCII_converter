package app.handlers

import handler.{Handler, SimpleHandler}

/**
 * Basic handler for handling commands from imputed arguments.
 * Based on level on (in)correctness of command and number of arguments for given command, up to two elements from args can be removed by handle function
 */
trait CommandHandler extends SimpleHandler[List[String]] {
	def handle(args: List[String]): Option[Handler[List[String]]]
}
