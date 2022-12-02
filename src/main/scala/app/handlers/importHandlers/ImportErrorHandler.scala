package app.handlers.importHandlers

import app.handlers.{Handler, SimpleHandler}

import java.io.OutputStream

class ImportErrorHandler(out: OutputStream) extends SimpleHandler[Nothing] {

	override def handle(args: Nothing): Option[Handler[Nothing]] = {
		out.write("Unsupported file extension".getBytes())
		out.flush()
		super.handle(args)
	}
}
