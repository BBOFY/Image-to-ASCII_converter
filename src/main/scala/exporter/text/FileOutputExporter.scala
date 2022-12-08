package exporter.text

import java.io.{File, FileOutputStream}

class FileOutputExporter(path: String)
  extends StreamTextExporter(new FileOutputStream(new File(path))) {

}
