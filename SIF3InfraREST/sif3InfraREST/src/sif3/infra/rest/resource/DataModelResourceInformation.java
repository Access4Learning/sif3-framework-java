package sif3.infra.rest.resource;

import java.util.List;

import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryJoinType;
import sif3.common.model.QueryOperator;
import sif3.common.model.QueryPredicate;

public class DataModelResourceInformation {

  private String objectNamePlural = null;
  private String objectName = null;
  private String mimeType = null;
  private String resourceId = null;
  private QueryCriteria queryCriteria = null;

  public DataModelResourceInformation(UriInfo uriInfo) {
    List<PathSegment> pathSegments = uriInfo.getPathSegments();

    if (pathSegments.size() == 2) { 
      // /requests/{ObjectNamePlural}
      String lastSegment = pathSegments.get(1).getPath();
      String[] parts = getNameAndExtension(lastSegment);
      this.objectNamePlural = parts[0];
      this.mimeType = parts[1];
    } else if (pathSegments.size() == 3) { 
      // /requests/{ObjectNamePlural}/{ResourceId}
      // /requests/{ObjectNamePlural}/{ObjectName}
      String firstSegment = pathSegments.get(1).getPath();
      String lastSegment = pathSegments.get(2).getPath();
      this.objectNamePlural = getNameAndExtension(firstSegment)[0];
      String[] parts = getNameAndExtension(lastSegment);
      this.resourceId = parts[0];
      this.objectName = parts[0];
      this.mimeType = parts[1];
    } else if (pathSegments.size() > 3) {
      // /requests/{QueryKeyPlural}/{QueryValue}/.../{ObjectNamePlural}
      this.queryCriteria = new QueryCriteria(QueryJoinType.AND);
      for (int i = 1; i < pathSegments.size() - 1; i += 2) {
        String name = pathSegments.get(i).getPath();
        String value = pathSegments.get(i + 1).getPath();
        this.queryCriteria.addPredicate(new QueryPredicate(name, QueryOperator.EQUAL, value));
      }
      String lastSegment = pathSegments.get(pathSegments.size() - 1).getPath();
      String[] parts = getNameAndExtension(lastSegment);
      this.objectNamePlural = parts[0];
      this.mimeType = parts[1];
    }
  }

  private String[] getNameAndExtension(String input) {
    String[] result = new String[] { input, null };
    if (input != null) {
      int index = input.lastIndexOf(".");
      if (index >= 0 && index < input.length() - 1) {
        result[0] = input.substring(0, index);
        result[1] = input.substring(index + 1);
      }
    }
    return result;
  }

  public String getObjectNamePlural() {
    return objectNamePlural;
  }

  public void setObjectNamePlural(String objectNamePlural) {
    this.objectNamePlural = objectNamePlural;
  }

  public String getObjectName() {
    return objectName;
  }

  public void setObjectName(String objectName) {
    this.objectName = objectName;
  }

  public String getMimeType() {
    return mimeType;
  }

  public void setMimeType(String mimeType) {
    this.mimeType = mimeType;
  }

  public String getResourceId() {
    return resourceId;
  }

  public void setResourceId(String resourceId) {
    this.resourceId = resourceId;
  }

  public QueryCriteria getQueryCriteria() {
    return queryCriteria;
  }

  public void setQueryCriteria(QueryCriteria queryCriteria) {
    this.queryCriteria = queryCriteria;
  }

  public boolean isGetSingle() {
    return getQueryCriteria() == null && getResourceId() != null;
  }

  public boolean isGetMany() {
    return getQueryCriteria() == null && getResourceId() == null;
  }

  public boolean isServicePathQuery() {
    return getQueryCriteria() != null;
  }

  public boolean isCreateMany() {
    return getObjectName() == null;
  }

  public boolean isCreateSingle() {
    return getObjectName() != null;
  }

  public boolean isUpdateMany() {
    return getResourceId() == null;
  }

  public boolean isUpdateSingle() {
    return getResourceId() != null;
  }

  public boolean isDeleteMany() {
    return getResourceId() == null;
  }

  public boolean isDeleteSingle() {
    return getResourceId() != null;
  }
}
