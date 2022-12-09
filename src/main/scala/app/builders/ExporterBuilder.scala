package app.builders

import exporter.text.TextExporter

class ExporterBuilder extends Builder[TextExporter, Seq[TextExporter]] {

	protected var _exporters: Seq[TextExporter] = Seq.empty

	override def registerProperty(exporter: TextExporter): Unit = {
		_exporters = _exporters.appended(exporter)
	}

	override def build: Seq[TextExporter] = _exporters
}
