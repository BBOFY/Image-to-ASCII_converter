package app.handlers.importHandlers

import app.importers.ImporterJpg
import app.inputParser.InputParser
import app.processor.ImageProcessor

class ImportJpgHandler(val imgProcessor: ImageProcessor,
					   val parser: InputParser[String],
					   val importer: ImporterJpg = new ImporterJpg
					  )
  extends ImportIoImageHandler(importer, imgProcessor, parser) {
	override def validPostfixes: Seq[String] = Seq(
		".jpg", ".jpeg", ".jpe", ".jif", ".jfif", ".jfi",
		".JPG", ".JPEG", ".JPE", ".JIF", ".JFIF", ".JFI"
	)
}