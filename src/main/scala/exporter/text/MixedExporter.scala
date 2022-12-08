package exporter.text

class MixedExporter(exporters: Seq[TextExporter]) extends TextExporter {
	override def `export`(item: String): Unit = {
		exporters.foreach(exporter => {
			exporter.`export`(item)
		})
	}
}
