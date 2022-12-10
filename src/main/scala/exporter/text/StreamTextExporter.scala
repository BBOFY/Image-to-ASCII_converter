package exporter.text

import java.io.OutputStream

/**
 * Exporter for exporting text into desired output stream
 * @param outputStream Stream where text will be outputted
 */
class StreamTextExporter(outputStream: OutputStream) extends TextExporter {
  private var closed = false

  protected def exportToStream(text: String): Unit ={

    if (closed)
      throw new Exception("The stream is already closed")

    outputStream.write(text.getBytes("UTF-8"))
    outputStream.flush()
  }

  def close(): Unit = {
    if (closed)
      return

    outputStream.close()
    closed = true
  }

  override def export(item: String): Unit = exportToStream(item)
}
