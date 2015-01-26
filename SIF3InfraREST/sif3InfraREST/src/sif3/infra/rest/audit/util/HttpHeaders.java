package sif3.infra.rest.audit.util;

import java.util.Collection;

public interface HttpHeaders {

  public Collection<String> getHeaderNamesCollection();
  public Collection<String> getHeadersCollection(String name);
  
}
