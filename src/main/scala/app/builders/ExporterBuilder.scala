package app.builders

import exporter.text.TextExporter

class ExporterBuilder extends Builder[TextExporter, Seq[TextExporter]] {

	protected var exporters: Seq[TextExporter] = Seq.empty

	override def registerProperty(exporter: TextExporter): Unit = {
		exporters = exporters.appended(exporter)
	}

	override def build: Seq[TextExporter] = exporters
}
