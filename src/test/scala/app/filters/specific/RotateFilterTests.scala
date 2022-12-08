package app.filters.specific

import app.filters.VariableFilterTests
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class RotateFilterTests extends VariableFilterTests {

	protected val rotateFilter = new RotateFilter

	protected val refImgRotated90 = new ImageGrey(Vector(
		Vector(PixelGrey(2), PixelGrey(5)),
		Vector(PixelGrey(1), PixelGrey(4)),
		Vector(PixelGrey(0), PixelGrey(3))
	))
	protected val refImgRotated180 = new ImageGrey(Vector(
		Vector(PixelGrey(5), PixelGrey(4), PixelGrey(3)),
		Vector(PixelGrey(2), PixelGrey(1), PixelGrey(0))
	))
	protected val refImgRotated270 = new ImageGrey(Vector(
		Vector(PixelGrey(3), PixelGrey(0)),
		Vector(PixelGrey(4), PixelGrey(1)),
		Vector(PixelGrey(5), PixelGrey(2))
	))
// ### 0° #########################
	validFilterTest(
		"Rotate 0°",
		rotateFilter,
		_testImg0,
		_testImg0,
		0
	)
	validFilterTest(
		"Rotate 360°",
		rotateFilter,
		_testImg0,
		_testImg0,
		360
	)
	validFilterTest(
		"Rotate -720°",
		rotateFilter,
		_testImg0,
		_testImg0,
		-720
	)
// ### 90° ########################
	validFilterTest(
		"Rotate 90°",
		rotateFilter,
		_testImg0,
		refImgRotated90,
		90
	)
	validFilterTest(
		"Rotate -2070°",
		rotateFilter,
		_testImg0,
		refImgRotated90,
		-2070
	)
	validFilterTest(
		"Rotate 4050°",
		rotateFilter,
		_testImg0,
		refImgRotated90,
		4050
	)
// ### 180° #######################
	validFilterTest(
	"Rotate 180°",
		rotateFilter,
		_testImg0,
		refImgRotated180,
		180
	)
	validFilterTest(
		"Rotate -540°",
		rotateFilter,
		_testImg0,
		refImgRotated180,
		-540
	)
	validFilterTest(
		"Rotate 11700°",
		rotateFilter,
		_testImg0,
		refImgRotated180,
		11700
	)
// ### 270° ######################
	validFilterTest(
		"Rotate 270°",
		rotateFilter,
		_testImg0,
		refImgRotated270,
		270
	)
	validFilterTest(
		"Rotate -90°",
		rotateFilter,
		_testImg0,
		refImgRotated270,
		-90
	)
	validFilterTest(
		"Rotate 2430°",
		rotateFilter,
		_testImg0,
		refImgRotated270,
		2430
	)
// ### Custom ################
	protected val imgSmall = new ImageGrey(Vector(Vector(PixelGrey(5))))
	validFilterTest("Rotate small 0°", rotateFilter,
		imgSmall, imgSmall, 0
	)
	validFilterTest("Rotate small 90°", rotateFilter,
		imgSmall, imgSmall, 90
	)
	validFilterTest("Rotate small 180°", rotateFilter,
		imgSmall, imgSmall, 180
	)
	validFilterTest("Rotate small 270°", rotateFilter,
		imgSmall, imgSmall, 270
	)

	test("Invalid values") {
		assertThrows[IllegalArgumentException](
			rotateFilter.setValue(15)
		)
		assertThrows[IllegalArgumentException](
			rotateFilter.setValue(-493)
		)
		assertThrows[IllegalArgumentException](
			rotateFilter.setValue(139)
		)
		assertThrows[IllegalArgumentException](
			rotateFilter.setValue(-798451)
		)
	}

}

