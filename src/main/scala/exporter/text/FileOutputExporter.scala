package exporter.text

import java.io.{File, FileOutputStream}

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
