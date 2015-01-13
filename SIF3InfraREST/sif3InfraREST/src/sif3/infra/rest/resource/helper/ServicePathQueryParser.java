package sif3.infra.rest.resource.helper;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.ws.rs.core.PathSegment;
import javax.ws.rs.core.UriInfo;

import org.apache.commons.lang.StringUtils;

import sif3.common.model.QueryCriteria;
import sif3.common.model.QueryJoinType;
import sif3.common.model.QueryOperator;
import sif3.common.model.QueryPredicate;

public class ServicePathQueryParser {
  private static final String FULL_NAME = "fullname";
  private static final String NAME = "name";
  private static final String EXTENSION = "ext";
  private static final Pattern NAME_AND_EXTENSION_PATTERN = Pattern.compile("^(?<" + FULL_NAME + ">[^\\.]*)$|^(?<" + NAME + ">.*)\\.(?<" + EXTENSION + ">[^\\.]*)$");
  private QueryCriteria queryCriteria = null;
  private String servicePath = null;
  private String objectNamePlural = null;

  public ServicePathQueryParser(UriInfo uriInfo) {
    List<PathSegment> pathSegments = uriInfo.getPathSegments();
    // We must have more than 3 segments. shortest valid service path query :
    //    /requests/parent/key/target
    // We must have an even number of segments :
    //    /requests{/parent/key}/target
    if (pathSegments.size() > 3 && pathSegments.size() % 2 == 0) {
      String prefix = "";
      this.servicePath = "";
      this.queryCriteria = new QueryCriteria(QueryJoinType.AND);
      for (int i = 1; i < pathSegments.size() - 1; i += 2) {
        String name = pathSegments.get(i).getPath();
        String value = pathSegments.get(i + 1).getPath();
        this.queryCriteria.addPredicate(new QueryPredicate(name, QueryOperator.EQUAL, value));
        servicePath += prefix + name + "/{}"; 
        prefix = "/";
      }
      Matcher matcher = NAME_AND_EXTENSION_PATTERN.matcher(pathSegments.get(pathSegments.size() - 1).getPath());
      if (matcher.matches()) {
        this.objectNamePlural = StringUtils.defaultString(matcher.group(FULL_NAME), matcher.group(NAME));
        servicePath += prefix + this.objectNamePlural;
      }
    }
  }
  
  public QueryCriteria getQueryCriteria() {
    return queryCriteria;
  }
  
  public String getServicePath() {
    return servicePath;
  }
  
  public String getObjectNamePlural() {
    return objectNamePlural;
  }
  
  public boolean isValid() {
    return servicePath != null && queryCriteria != null && queryCriteria.size() > 0;
  }

}
