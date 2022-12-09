package app.handlers.importHandlers

import app.Commands
import app.importers.ImporterJpg
import app.inputParser.InputParser
import app.processor.ImageProcessor
import handler.Handler

class ImportJpgHandler(val imgProcessor: ImageProcessor,
					   val parser: InputParser[String],
					   val importer: ImporterJpg = new ImporterJpg
					  )
  extends ImportIoImageHandler(importer, imgProcessor, parser) {
	override val validPostfixes: Seq[String] = Seq(
		".jpg", ".jpeg", ".jpe", ".jif", ".jfif", ".jfi",
		".JPG", ".JPEG", ".JPE", ".JIF", ".JFIF", ".JFI"
	)

//	override def handle(args: List[String]): Option[Handler[List[String]]] = {
//		if (args.head == Commands.image
//		  && validPostfixes.exists(postfix => args.apply(1).endsWith(postfix))) {
//			importer.setPath(args.apply(1))
//			imgProcessor.loadImage(importer.doImport())
//			parser.removeElements(2)
//			None
//		}
//		else super.handle(args)
//	}
}