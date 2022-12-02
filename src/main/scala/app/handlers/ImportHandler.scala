package app.handlers

abstract class ImportHandler extends SimpleHandler[String] {

	protected def validCommandForms: Seq[String]
	def handle(path: String): Option[Handler[String]]
}
