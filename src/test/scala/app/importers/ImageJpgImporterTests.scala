package app.importers

import app.models.image.ImageRgb
import app.models.pixel.PixelRgb
import org.scalatest.FunSuite

import java.io.IOException

/**
 * Due to imprecise compression of jpg format, comparing individual values of pixels of imported image is not really useful
 */
class ImageJpgImporterTests extends FunSuite {

	val validPath = "src/main/resources/test_jpg.jpg"
	val invalidPath = "src/main/resources/test"
	val importer = new ImporterJpg

	// (Sub)pixel values found with GIMP2. They are not exact with ImageIO however
	val refImg = new ImageRgb(Vector(
		Vector(PixelRgb(253, 0, 0), PixelRgb(10, 245, 5), PixelRgb(0, 7, 241), PixelRgb(255, 253, 248)),
		Vector(PixelRgb(254, 255, 12), PixelRgb(255, 1, 255), PixelRgb(2, 255, 255), PixelRgb(243, 255, 251)),
		Vector(PixelRgb(1, 0, 0), PixelRgb(69, 63, 65), PixelRgb(116, 131, 124), PixelRgb(200, 184, 197))
	))

	test("Valid path") {
		importer.setPath(validPath)
		val img: ImageRgb = importer.doImport()
		assertResult(4) {
			img.width
		}
		assertResult(3) {
			img.height
		}
//		assert(img.getGrid == refImg.getGrid)
	}

	test("Invalid path") {
		importer.setPath(invalidPath)
		assertThrows[IOException] {
			importer.doImport()
		}
	}

}
