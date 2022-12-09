package exporters.text

import exporter.text.FileOutputExporter
import org.scalatest.FunSuite

class FileOutputExporterTests extends FunSuite
  with TestWithFiles{

  test("No file exists"){
    val fileName = getTestFile

    try {
      ensureDeleted(fileName)

      val exporter = new FileOutputExporter(fileName)

      exporter.export("Ahoj")
      exporter.close()

      assertFileContent(fileName, "Ahoj")
    }
    finally {
      ensureDeleted(fileName)
    }
  }

  test("File already exists"){
    val fileName = getTestFile

    try{
      ensureCreated(fileName)

      val exporter = new FileOutputExporter(fileName)

      exporter.export("Ahoj")
      exporter.close()

      assertFileContent(fileName, "Ahoj")
    }
    finally{
      ensureDeleted(fileName)
    }
  }
}
