package app.importers

import app.models.image.ImageRgb
import app.models.pixel.PixelRgb

import java.io.IOException

class ImagePngImporterTests extends ImporterTests {

	val validPath = "src/main/resources/test_png.png"
	val invalidPath = "/dev/null/bob.png"
	val importer: ImporterImageIo = new ImporterPng

	val refImg = new ImageRgb(Vector(
		Vector(PixelRgb(255, 0, 0), PixelRgb(0, 255, 0), PixelRgb(0, 0, 255), PixelRgb(255, 255, 255)),
		Vector(PixelRgb(255, 255, 0), PixelRgb(255, 0, 255), PixelRgb(0, 255, 255), PixelRgb(255, 255, 255)),
		Vector(PixelRgb(0, 0, 0), PixelRgb(63, 63, 63), PixelRgb(127, 127, 127), PixelRgb(191, 191, 191))
	))

	importer.setPath(validPath)
	validTestImage(
		"Valid path",
		refImg,
		importer
	)

	test("Invalid path") {
		importer.setPath(invalidPath)
		assertThrows[IOException] {
			importer.doImport()
		}
	}
}
