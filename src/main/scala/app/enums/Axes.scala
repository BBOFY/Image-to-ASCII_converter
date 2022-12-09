package app.enums

object Axes extends Enumeration {
	type Axis = Value

	val x: Axes.Value = Value("x")
	val y: Axes.Value = Value("y")

	def isAxis(axis: String): Boolean = values.exists(_.toString == axis)
}
