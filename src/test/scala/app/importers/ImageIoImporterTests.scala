package app.importers

import org.scalatest.FunSuite

class ImageIoImporterTests extends FunSuite {

	test("importer") {

		val importer = new ImporterJpg("src/main/resources/photo.jpg")

		importer.doImport()


	}

}
