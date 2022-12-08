package exporters.text

import exporter.text.StreamTextExporter
import org.scalatest.FunSuite

import java.io.ByteArrayOutputStream

class StreamTextTests extends FunSuite {
  test("Write string to stream"){
    val stream = new ByteArrayOutputStream()
    val exporter = new StreamTextExporter(stream)

    exporter.export("Why am I doing this so late ||I hadn't much time, but still||")

    assert(stream.toString("UTF-8") == "Why am I doing this so late ||I hadn't much time, but still||")
  }
}
