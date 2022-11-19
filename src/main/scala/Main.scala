import scala.io.Source

import java.io.File
import javax.imageio.ImageIO
import java.awt.Image
import java.awt.image.BufferedImage

object Main extends App {
//	val photo3: BufferedImage = ImageIO.read(new File("src/main/resources/photo.jpg"))


//		for (x <- 0 until photo3.getWidth) {
//			println( photo3.getRGB(x, 0).toHexString)
//		}



//	val image: Image = photo3.getScaledInstance(100, 100, Image.SCALE_DEFAULT)

//	ImageIO.write(convertToBufferedImage(photo3), "png", new File("D:/aMyStuff/CVUT/5. Semester/BI-OOP/bi-oop-semestral-work/src/main/resources/out.png"))

	def convertToBufferedImage(img: Image): BufferedImage = {
		img match {
			case image: BufferedImage => return image
			case _ =>
		}
		// Create a buffered image with transparency
		val bi = new BufferedImage(img.getWidth(null), img.getHeight(null), BufferedImage.TYPE_INT_ARGB)
		val graphics2D = bi.createGraphics
		graphics2D.drawImage(img, 0, 0, null)
		graphics2D.dispose()
		bi
	}

}