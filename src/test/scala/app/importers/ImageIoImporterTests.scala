package app.importers

import org.scalatest.FunSuite

class ImageIoImporterTests extends FunSuite {

	test("importing") {

		val importer = new ImporterImageIo("src/main/resources/photo.jpg")

		importer.doImport()

	}

}
