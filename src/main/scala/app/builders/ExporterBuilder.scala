package app.builders

import exporter.text.TextExporter

class ExporterBuilder extends Builder[TextExporter, Seq[TextExporter]] {

	protected var _exporters: Seq[TextExporter] = Seq.empty

	/**
	 *  Saves given exporter into sequence of exporter, which will be later given to calling entity
	 *  @param exporter Exporter to be saved to sequence
	 */
	override def registerProperty(exporter: TextExporter): Unit = {
		_exporters = _exporters.appended(exporter)
	}

	/**
	 *  @return Sequence of exporters, that were registered previously, or empty sequence, if no exporter was registered
	 */
	override def build: Seq[TextExporter] = _exporters
}
