package exporter.text

/**
 * Exports text into standard output stream
 */
class StdOutputExporter extends StreamTextExporter(System.out) {
	override def equals(obj: Any): Boolean = {
		obj match {
			case _: StdOutputExporter => true
			case _ => false
		}
	}
}
