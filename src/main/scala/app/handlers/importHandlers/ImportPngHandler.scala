package app.handlers.importHandlers

import app.Commands
import app.importers.ImporterPng
import app.inputParser.InputParser
import app.processor.ImageProcessor
import handler.Handler

class ImportPngHandler(val imgProcessor: ImageProcessor,
					   val parser: InputParser[String],
					   val importer: ImporterPng = new ImporterPng
					  )
  extends ImportIoImageHandler(importer, imgProcessor, parser) {
	override protected val validPostfixes: Seq[String] = Seq(
		".png", ".PNG"
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
