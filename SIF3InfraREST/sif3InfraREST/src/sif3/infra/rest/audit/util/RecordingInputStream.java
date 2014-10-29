package sif3.infra.rest.audit.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletInputStream;

public class RecordingInputStream extends ServletInputStream {

  protected InputStream inputStream = null;
  private ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();

  public RecordingInputStream(InputStream inputStream) {
    this.inputStream = inputStream;
  }

  @Override
  public int available() throws IOException {
    return inputStream.available();
  }

  @Override
  public void close() throws IOException {
    inputStream.close();
  }

  @Override
  public synchronized void mark(int readlimit) {
    inputStream.mark(readlimit);
  }

  @Override
  public boolean markSupported() {
    return inputStream.markSupported();
  }

  @Override
  public int read() throws IOException {
    int result = inputStream.read();
    byteArrayOutputStream.write(result);
    return result;
  }

  @Override
  public synchronized void reset() throws IOException {
    inputStream.reset();
  }
  
  public String getContent() {
    return new String(byteArrayOutputStream.toByteArray());
  }
}
