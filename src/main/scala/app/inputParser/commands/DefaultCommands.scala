package app.inputParser.commands

class DefaultCommands extends Commands {
	/**
	 * Two piece argument
	 */
	override def cmdImage = "--image"
	/**
	 * One piece argument
	 */
	override def cmdImageRandom = "--image-random"
	/**
	 * Two piece argument
	 */
	override def cmdTable = "--table"
	/**
	 * Two piece argument
	 */
	override def cmdTableCustom = "--custom-table"
	/**
	 * Two piece argument
	 */
	override def cmdFilterRotate = "--rotate"
	/**
	 * Two piece argument
	 */
	override def cmdFilterScale = "--scale"
	/**
	 * One piece argument
	 */
	override def cmdFilterInv = "--invert"
	/**
	 * Two piece argument
	 */
	override def cmdFilterFlip = "--flip"
	/**
	 * Two piece argument
	 */
	override def cmdFilterBright = "--brightness"
	/**
	 * one piece argument
	 */
	override def cmdOutputConsole = "--output-console"
	/**
	 * Two piece argument
	 */
	override def cmdOutputFile = "--output-file"
}
