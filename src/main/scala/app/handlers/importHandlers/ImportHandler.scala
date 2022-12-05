package app.handlers.importHandlers

import app.handlers.{Handler, SimpleHandler}

abstract class ImportHandler extends SimpleHandler[String] {

	protected def validCommandForms: Seq[String]
	def handle(path: String): Option[Handler[String]]
}
