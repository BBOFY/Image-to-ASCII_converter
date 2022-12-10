package app.enums

/**
 * Enum holding all valid axis values the user can input as argument for '--flip' command
 */
object Axes extends Enumeration {
	type Axis = Value

	val x: Axes.Value = Value("x")
	val y: Axes.Value = Value("y")

	def isAxis(axis: String): Boolean = values.exists(_.toString == axis)
}
