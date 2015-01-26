package sif3.infra.rest.audit.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

public class TeeWriter extends PrintWriter {
  protected Writer branch;
  protected Writer out;

  public TeeWriter(Writer out, Writer branch) {
    super(out);
    this.out = out;
    this.branch = branch;
  }

  @Override
  public void write(char[] cbuf) {
    try {
      this.out.write(cbuf);
    } catch (IOException ignore) {}
    try {
      this.branch.write(cbuf);
    } catch (IOException ignore) {}
  }

  @Override
  public void write(char[] cbuf, int off, int len) {
    try {
      this.out.write(cbuf, off, len);
    } catch (IOException ignore) {}
    try {
      this.branch.write(cbuf, off, len);
    } catch (IOException ignore) {}
  }

  @Override
  public void write(String str) {
    try {
      this.out.write(str);
    } catch (IOException ignore) {}
    try {
      this.branch.write(str);
    } catch (IOException ignore) {}
  }

  @Override
  public void write(String str, int off, int len) {
    try {
      this.out.write(str, off, len);
    } catch (IOException ignore) {}
    try {
      this.branch.write(str, off, len);
    } catch (IOException ignore) {}
  }
  
  @Override
  public synchronized void write(int b) {
    try {
      this.out.write(b);
    } catch (IOException ignore) {}
    try {
      this.branch.write(b);
    } catch (IOException ignore) {}
  }

  @Override
  public void flush() {
    try {
      this.out.flush();
    } catch (IOException ignore) {}
    try {
      this.branch.flush();
    } catch (IOException ignore) {}
  }

  @Override
  public void close() {
    try {
      this.out.close();
    } catch (IOException ignore) {}
    try {
      this.branch.close();
    } catch (IOException ignore) {}
  }
}
