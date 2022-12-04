package app.importers

import app.models.image.ImageRgb
import org.scalatest.FunSuite

class ImagePngImporterTests extends FunSuite {

	test("importer") {

		val importer = new ImporterPng("src/main/resources/test_png.png")
		val img: ImageRgb = importer.doImport()

		for (x <- 0 until img.width) {
			for (y <- 0 until img.height) {
				println(img.getPixel(x, y))
			}
		}

	}

}
