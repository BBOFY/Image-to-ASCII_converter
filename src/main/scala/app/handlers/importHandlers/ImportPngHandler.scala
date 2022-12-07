package app.handlers.importHandlers

import app.importers.ImporterPng
import app.inputParser.InputParser
import app.processor.ImageProcessor

class ImportPngHandler(val imgProcessor: ImageProcessor,
					   val parser: InputParser[String],
					   val importer: ImporterPng = new ImporterPng
					  )
  extends ImportIoImageHandler(importer, imgProcessor, parser) {
	override protected def validPostfixes: Seq[String] = Seq(
		".png", ".PNG"
	)
}
