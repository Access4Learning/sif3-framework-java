package sif3.infra.rest.audit.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Collection;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpServletResponseWrapper;

public class AuditResponseWrapper extends HttpServletResponseWrapper implements HttpHeaders {

  private ByteArrayOutputStream outputStream = null;
  private TeeOutputStream teeOutputStream = null;

  public AuditResponseWrapper(HttpServletResponse response) {
    super(response);
  }

  @Override
  public ServletOutputStream getOutputStream() throws IOException {
    this.outputStream = new ByteArrayOutputStream();
    this.teeOutputStream = new TeeOutputStream(super.getOutputStream(), this.outputStream);
    return this.teeOutputStream;
  }

  public String getContent() {
    String result = null;
    if (outputStream != null) {
      result = new String(outputStream.toByteArray());
    }
    return result;
  }

  @Override
  public Collection<String> getHeaderNamesCollection() {
    return super.getHeaderNames();
  }

  @Override
  public Collection<String> getHeadersCollection(String name) {
    return super.getHeaders(name);
  }
}
