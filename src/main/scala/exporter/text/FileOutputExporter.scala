package exporter.text

import java.io.{File, FileOutputStream}

/**
 * Exports text into file
 * @param path Path of file, where text would be exported
 */
class FileOutputExporter(path: String)
  extends StreamTextExporter(new FileOutputStream(new File(path))) {

	protected val _path: String = path
	override def equals(obj: Any): Boolean = {
		obj match {
			case that: FileOutputExporter => that._path == this._path
			case _ => false
		}
	}
}
