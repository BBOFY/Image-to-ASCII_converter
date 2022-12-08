package app.importers

import app.models.image.ImageRgb
import org.scalatest.FunSuite

abstract class ImporterTests extends FunSuite {
	protected def validTestImage(name: String,
								 refImg: ImageRgb,
								 importer: ImageImporter
								): Unit = {
		test(name) {
			val img: ImageRgb = importer.doImport()
			assertResult(4) {
				img.width
			}
			assertResult(3) {
				img.height
			}
			assert(
				img.getGrid == refImg.getGrid
			)
		}
	}
}
