package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.logging.Logger;

/**
 * This class transforms the streams of character sent to the decorated writer.
 * When filter encounters a line separator, it sends it to the decorated writer.
 * It then sends the line number and a tab character, before resuming the write
 * process.
 *
 * Hello\n\World -> 1\Hello\n2\tWorld
 *
 * @author Olivier Liechti
 */
public class FileNumberingFilterWriter extends FilterWriter {

  private static final Logger LOG = Logger.getLogger(FileNumberingFilterWriter.class.getName());
  private int lineNumber = 1;
  private boolean isFirstCalled = true;
  private boolean isSeparator = false;
  private boolean isDoubleSeparator = false;
  public FileNumberingFilterWriter(Writer out) {
    super(out);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    str = str.substring(off, off + len);
    String[] parts = str.split("(?<=\n|\r(?!\n))");
    String filtered = new String();
    for (String part : parts) {
      if (isFirstCalled && !isSeparator) {
        filtered += lineNumber + "\t"; // We start by adding the line number and \t
        isFirstCalled = false; // Duh...
      }
      filtered += part; // We add the string/part of string
      isDoubleSeparator = isSeparator && (len == 1);
      isSeparator = part.endsWith("\n") || part.endsWith("\r");
      if(isSeparator && !isDoubleSeparator){ // separator found
        lineNumber++;
        filtered += lineNumber + "\t";
        }
      isDoubleSeparator = false;

    }
     out.write(filtered);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    this.write(cbuf.toString(), off, len);
  }

  @Override
  public void write(int c) throws IOException {
    boolean isSeparatorInt = (c == '\r' || c == '\n');
    StringBuilder sb = new StringBuilder();
    if(isFirstCalled && !isSeparator) {
      isFirstCalled = false;
      out.write(lineNumber + "\t");
      // for the return
    }

    if(isSeparator && !isSeparatorInt){
      lineNumber++;
      out.write(lineNumber + "\t");
    }

    out.write(c);
    isSeparator = isSeparatorInt;
  }
}
