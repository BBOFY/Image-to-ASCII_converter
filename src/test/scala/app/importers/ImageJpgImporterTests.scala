package app.importers

import app.models.image.ImageRgb
import org.scalatest.FunSuite

class ImageJpgImporterTests extends FunSuite {

	test("importer") {

		val path = "src/main/resources/test_jpg.jpg"
		val importer = new ImporterJpg
		importer.setPath(path)
		val img: ImageRgb = importer.doImport()

		println(img.getPixel(1, 0))


	}

}
