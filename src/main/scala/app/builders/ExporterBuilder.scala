package app.builders

import exporter.text.{MixedExporter, TextExporter}

class ExporterBuilder extends Builder[TextExporter, TextExporter] {

	private var exporters: Seq[TextExporter] = Seq.empty

	override def registerProperty(exporter: TextExporter): Unit = {
		exporters = exporters.appended(exporter)
	}

	override def build: TextExporter = new MixedExporter(exporters)
}
