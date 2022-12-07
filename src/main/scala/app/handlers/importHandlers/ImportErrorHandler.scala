package app.handlers.importHandlers

import handler.{Handler, SimpleHandler}

import java.io.OutputStream

class ImportErrorHandler(out: OutputStream) extends SimpleHandler[List[String]] {

	override def handle(args: List[String]): Option[Handler[List[String]]] = {
		out.write("Unsupported file extension".getBytes())
		out.flush()
		super.handle(args)
	}
}
