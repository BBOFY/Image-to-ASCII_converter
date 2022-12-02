package app.handlers

trait CommandHandler extends SimpleHandler[String] {

	def validCommandForms: Seq[String]
	protected def processCommand(args: Seq[String]): Unit
	def handle(command: String): Option[Handler[String]]
}
