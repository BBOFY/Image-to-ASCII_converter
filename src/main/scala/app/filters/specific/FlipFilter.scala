package app.filters.specific

import app.filters.ImageFilter
import app.models.image.ImageGrey
import app.models.pixel.PixelGrey

class FlipFilter(val flipAxis: String) extends ImageFilter {

	override def apply(image: ImageGrey): ImageGrey = {
		val grid = image.getGrid
		var newGrid: Vector[Vector[PixelGrey]] = Vector.empty

		flipAxis match {
			case "x" | "X" => newGrid = grid.reverse
			case "y" | "Y" => 
				for ( row <- grid ) {
					newGrid = newGrid.appended(row.reverse)
				}
		}

		new ImageGrey(newGrid)

	}
}
