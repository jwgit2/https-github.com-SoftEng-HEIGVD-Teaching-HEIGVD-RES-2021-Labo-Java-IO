package ch.heigvd.res.labio.impl.filters;

import java.io.FilterWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.Locale;

/**
 *
 * @author Olivier Liechti
 */
public class UpperCaseFilterWriter extends FilterWriter {
  
  public UpperCaseFilterWriter(Writer wrappedWriter) {
    super(wrappedWriter);
  }

  @Override
  public void write(String str, int off, int len) throws IOException {
    super.write(str.toUpperCase(), off, len);
  }

  @Override
  public void write(char[] cbuf, int off, int len) throws IOException {
    char[] toUpper = new char[cbuf.length];

    for (int i = 0; i < cbuf.length;  i++){
      toUpper[i] = Character.toUpperCase(cbuf[i]);
    }
    super.write(toUpper, off, len);
  }

  @Override
  public void write(int c) throws IOException {
    int toUpper = Character.toUpperCase(c);
    super.write(toUpper);
  }

}
