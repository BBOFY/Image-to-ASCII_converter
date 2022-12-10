package app.importers

import app.models.image.ImageRgb

class GenerateImportTests extends ImporterTests {

	private val importer = new ReallyPrimitiveImageGenerator

	/**
	 * Cannot test the exact values, only validity
	 */
	test("Random image import") {
		val img = importer.doImport()

		assert(img.width > 0)
		assert(img.height > 0)
		assert(img.isInstanceOf[ImageRgb])
	}

}
