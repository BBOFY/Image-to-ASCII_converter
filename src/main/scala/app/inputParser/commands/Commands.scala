package app.inputParser.commands

trait Commands {

	/**
	 * Two piece argument
	 */
	def cmdImage: String

	/**
	 * One piece argument
	 */
	def cmdImageRandom: String

	/**
	 * Two piece argument
	 */
	def cmdTable: String

	/**
	 * Two piece argument
	 */
	def cmdTableCustom: String

	/**
	 * Two piece argument
	 */
	def cmdFilterRotate: String

	/**
	 * Two piece argument
	 */
	def cmdFilterScale: String

	/**
	 * One piece argument
	 */
	def cmdFilterInv: String

	/**
	 * Two piece argument
	 */
	def cmdFilterFlip: String

	/**
	 * Two piece argument
	 */
	def cmdFilterBright: String

	/**
	 * one piece argument
	 */
	def cmdOutputConsole: String

	/**
	 * Two piece argument
	 */
	def cmdOutputFile: String

}
