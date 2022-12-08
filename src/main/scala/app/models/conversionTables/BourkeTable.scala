package app.models.conversionTables

/**
 * Conversion table with following sequence (from the darkest to the brightest):
 * "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\|()1{}[]?-_+~<>i!lI;:,"^`'  "
 *
 * Switched dot '.' for space ' ' for better white clarity
 *
 */
case class BourkeTable() extends LinearTable {
	_table  = "$@B%8&WM#*oahkbdpqwmZO0QLCJUYXzcvunxrjft/\\|()1{}[]?-_+~<>i!lI;:,\"^`'  "
}
