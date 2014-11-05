package sif3.infra.rest.resource;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class DataModelResourceInformation {
  
  private static final Pattern INFORMATION_PATTERN = Pattern.compile("^([^/\\.]*)$|^([^/\\.]*)\\.([^/\\.]*)$|^([^/\\.]*)/([^/\\.]*)$|^([^/\\.]*)/([^/\\.]*)\\.([^/\\.]*)$|^([^/\\.]*)/([^/\\.]*)/([^/\\.]*)$|^([^/\\.]*)/([^/\\.]*)/([^/\\.]*)\\.([^/\\.]*)$");
  
  private String objectNamePlural = null;
  private String mimeType = null;
  private String resourceId = null;
  private String servicePathName = null;
  private String servicePathValue = null;
  
  public DataModelResourceInformation(String path) {
    Matcher matcher = INFORMATION_PATTERN.matcher(path);
    if (matcher.matches()) {
      this.objectNamePlural = path;
      if (matcher.group(1) != null) {
        this.objectNamePlural = matcher.group(1);
      } else if (matcher.group(2) != null) {
        this.objectNamePlural = matcher.group(2);
        this.mimeType = matcher.group(3);
      } else if (matcher.group(4) != null) {
        this.objectNamePlural = matcher.group(4);
        this.resourceId = matcher.group(5);
      } else if (matcher.group(6) != null) {
        this.objectNamePlural = matcher.group(6);
        this.resourceId = matcher.group(7);
        this.mimeType = matcher.group(8);
      } else if (matcher.group(9) != null) {
        this.objectNamePlural = matcher.group(11);
        this.servicePathName = matcher.group(9);
        this.servicePathValue = matcher.group(10);
      } else if (matcher.group(12) != null) {
        this.objectNamePlural = matcher.group(14);
        this.servicePathName = matcher.group(12);
        this.servicePathValue = matcher.group(13);
        this.mimeType = matcher.group(15);
      }
    }
  }

  public String getObjectNamePlural() {
    return objectNamePlural;
  }

  public String getObjectName() {
    return resourceId;
  }
  
  public String getMimeType() {
    return mimeType;
  }

  public String getResourceId() {
    return resourceId;
  }

  public String getServicePathName() {
    return servicePathName;
  }

  public String getServicePathValue() {
    return servicePathValue;
  }
  
  public boolean isGetMany() {
    return getServicePathName() == null && getResourceId() == null;
  }
  
  public boolean isCreateMany() {
    return getObjectName() == null;
  }
  
  public boolean isUpdateMany() {
    return getResourceId() == null;
  }
  
  public boolean isGetSingle() {
    return getServicePathName() == null && getResourceId() != null;
  }
  
  public boolean isCreateSingle() {
    return getObjectName() != null;
  }
  
  public boolean isUpdateSingle() {
    return getResourceId() != null;
  }
  
  public boolean isServicePath() {
    return getServicePathName() != null;
  }

  public boolean isRemoveMany() {
    return getResourceId() == null;
  }
  
  public boolean isRemoveSingle() {
    return getResourceId() != null;
  }

}
