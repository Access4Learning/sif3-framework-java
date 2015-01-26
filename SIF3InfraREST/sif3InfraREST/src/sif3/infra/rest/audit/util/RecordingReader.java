package sif3.infra.rest.audit.util;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Reader;

public class RecordingReader extends BufferedReader {
  
  protected Reader reader = null;
  private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

  public RecordingReader(BufferedReader reader) {
    super(reader);
    this.reader = reader;
  }
  
  @Override
  public void close() throws IOException {
    reader.close();
  }
  
  @Override
  public void mark(int readAheadLimit) throws IOException {
    reader.mark(readAheadLimit);
  }
  
  @Override
  public boolean markSupported() {
    return reader.markSupported();
  }
  
  private int recordRead() throws IOException {
    int result = reader.read();
    byteArrayOutputStream.write(result);
    return result;
  }
  
  @Override
  public int read(char[] cbuf, int off, int len) throws IOException {
    if (cbuf == null) {
      throw new NullPointerException();
    } else if (off < 0 || len < 0 || len > cbuf.length - off) {
      throw new IndexOutOfBoundsException();
    } else if (len == 0) {
      return 0;
    }

    int c = recordRead();
    if (c == -1) {
      return -1;
    }
    
    cbuf[off] = (char)c;
    int i = 1;
    try {
      for (; i < len ; i++) {
        c = recordRead();
        if (c == -1) {
          break;
        }
        cbuf[off + i] = (char)c;
      }
    } catch (IOException ee) {
    }
    return i;
  }
  
  @Override
  public boolean ready() throws IOException {
    return reader.ready();
  }
  
  @Override
  public void reset() throws IOException {
    reader.reset();
  }

  public String getContent() {
    return new String(byteArrayOutputStream.toByteArray());
  }
}
