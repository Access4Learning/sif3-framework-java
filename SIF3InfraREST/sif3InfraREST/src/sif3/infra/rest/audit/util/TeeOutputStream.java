package sif3.infra.rest.audit.util;

import java.io.IOException;
import java.io.OutputStream;

import javax.servlet.ServletOutputStream;

public class TeeOutputStream extends ServletOutputStream {

  protected OutputStream branch;
  protected OutputStream out;

  public TeeOutputStream(OutputStream out, OutputStream branch) {
    this.out = out;
    this.branch = branch;
  }
  
  @Override
  public synchronized void write(byte[] b) throws IOException {
    this.out.write(b);
    this.branch.write(b);
  }

  @Override
  public synchronized void write(byte[] b, int off, int len) throws IOException {
    this.out.write(b, off, len);
    this.branch.write(b, off, len);
  }

  @Override
  public synchronized void write(int b) throws IOException {
    this.out.write(b);
    this.branch.write(b);
  }

  @Override
  public void flush() throws IOException {
    this.out.flush();
    this.branch.flush();
  }

  @Override
  public void close() throws IOException {
    try {
      this.out.close();
    } finally {
      this.branch.close();
    }
  }
}
