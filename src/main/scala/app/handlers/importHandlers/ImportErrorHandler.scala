package app.handlers.importHandlers

import app.handlers.{Handler, SimpleHandler}

import java.io.OutputStream

class ImportErrorHandler(out: OutputStream) extends SimpleHandler[String] {

	override def handle(args: String): Option[Handler[String]] = {
		out.write("Unsupported file extension".getBytes())
		out.flush()
		super.handle(args)
	}
}
