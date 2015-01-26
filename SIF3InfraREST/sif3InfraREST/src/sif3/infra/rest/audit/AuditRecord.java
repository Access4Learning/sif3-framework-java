package sif3.infra.rest.audit;

import javax.ws.rs.core.MediaType;
import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * Value Object to hold all values of interest when auditing.
 */
public class AuditRecord implements Serializable {
  private static final long serialVersionUID = -2942306308081090696L;

  private Date requestTime;
  private Date responseTime;
  private String clientIp;
  private String url;
  private String solutionId;
  private String appKey;
  private String userToken;
  private String instanceId;
  private String context;
  private String zone;
  private String environmentToken;
  private String sessionToken;
  private String method;
  private String queryParameters;
  private String requestMediaType;
  private Map<String, Object> requestHeaders;
  private String request;
  private Integer httpStatus;
  private String responseMediaType;  
  private Map<String, Object> responseHeaders;
  private String response;

  public Date getRequestTime() {
    return requestTime;
  }

  public void setRequestTime(Date requestTime) {
    if ((this.requestTime == null || "".equals(this.requestTime)) && requestTime != null && !"".equals(requestTime)) {
      this.requestTime = requestTime;
    }
  }

  public Date getResponseTime() {
    return responseTime;
  }

  public void setResponseTime(Date responseTime) {
    if ((this.responseTime == null || "".equals(this.responseTime)) && responseTime != null && !"".equals(responseTime)) {
      this.responseTime = responseTime;
    }
  }

  public String getClientIp() {
    return clientIp;
  }

  public void setClientIp(String clientIp) {
    if ((this.clientIp == null || "".equals(this.clientIp)) && clientIp != null && !"".equals(clientIp)) {
      this.clientIp = clientIp;
    }
  }

  public String getUrl() {
    return url;
  }

  public void setUrl(String url) {
    if ((this.url == null || "".equals(this.url)) && url != null && !"".equals(url)) {
      this.url = url;
    }
  }

  public String getSolutionId() {
    return solutionId;
  }

  public void setSolutionId(String solutionId) {
    if ((this.solutionId == null || "".equals(this.solutionId)) && solutionId != null && !"".equals(solutionId)) {
      this.solutionId = solutionId;
    }
  }

  public String getAppKey() {
    return appKey;
  }

  public void setAppKey(String appKey) {
    if ((this.appKey == null || "".equals(this.appKey)) && appKey != null && !"".equals(appKey)) {
      this.appKey = appKey;
    }
  }

  public String getUserToken() {
    return userToken;
  }

  public void setUserToken(String userToken) {
    if ((this.userToken == null || "".equals(this.userToken)) && userToken != null && !"".equals(userToken)) {
      this.userToken = userToken;
    }
  }

  public String getInstanceId() {
    return instanceId;
  }

  public void setInstanceId(String instanceId) {
    if ((this.instanceId == null || "".equals(this.instanceId)) && instanceId != null && !"".equals(instanceId)) {
      this.instanceId = instanceId;
    }
  }

  public String getContext() {
    return context;
  }

  public void setContext(String context) {
    if ((this.context == null || "".equals(this.context)) && context != null && !"".equals(context)) {
      this.context = context;
    }
  }

  public String getZone() {
    return zone;
  }

  public void setZone(String zone) {
    if ((this.zone == null || "".equals(this.zone)) && zone != null && !"".equals(zone)) {
      this.zone = zone;
    }
  }

  public String getEnvironmentToken() {
    return environmentToken;
  }

  public void setEnvironmentToken(String environmentToken) {
    if ((this.environmentToken == null || "".equals(this.environmentToken)) && environmentToken != null
        && !"".equals(environmentToken)) {
      this.environmentToken = environmentToken;
    }
  }

  public String getSessionToken() {
    return sessionToken;
  }

  public void setSessionToken(String sessionToken) {
    if ((this.sessionToken == null || "".equals(this.sessionToken)) && sessionToken != null && !"".equals(sessionToken)) {
      this.sessionToken = sessionToken;
    }
  }

  public String getMethod() {
    return method;
  }

  public void setMethod(String method) {
    if ((this.method == null || "".equals(this.method)) && method != null && !"".equals(method)) {
      this.method = method;
    }
  }

  public String getQueryParameters() {
    return queryParameters;
  }

  public void setQueryParameters(String queryParameters) {
    if ((this.queryParameters == null || "".equals(this.queryParameters)) && queryParameters != null
        && !"".equals(queryParameters)) {
      this.queryParameters = queryParameters;
    }
  }

  public Map<String, Object> getRequestHeaders() {
    return requestHeaders;
  }

  public void setRequestHeaders(Map<String, Object> requestHeaders) {
    if ((this.requestHeaders == null || "".equals(this.requestHeaders)) && requestHeaders != null
        && !"".equals(requestHeaders)) {
      this.requestHeaders = requestHeaders;
    }
  }

  public Map<String, Object> getResponseHeaders() {
    return responseHeaders;
  }

  public void setResponseHeaders(Map<String, Object> responseHeaders) {
    if ((this.responseHeaders == null || "".equals(this.responseHeaders)) && responseHeaders != null
        && !"".equals(responseHeaders)) {
      this.responseHeaders = responseHeaders;
    }
  }

  public String getRequest() {
    return request;
  }

  public void setRequest(String request) {
    if ((this.request == null || "".equals(this.request)) && request != null && !"".equals(request)) {
      this.request = request;
    }
  }

  public Integer getHttpStatus() {
    return httpStatus;
  }

  public void setHttpStatus(Integer httpStatus) {
    if (this.httpStatus == null && httpStatus != null) {
      this.httpStatus = httpStatus;
    }
  }

  public String getResponse() {
    return response;
  }

  public void setResponse(String response) {
    if ((this.response == null || "".equals(this.response)) && response != null && !"".equals(response)) {
      this.response = response;
    }
  }

  public String getRequestMediaType() {
    return requestMediaType;
  }

  public void setRequestMediaType(MediaType mediaType) {
    if (mediaType != null) {
      this.requestMediaType = mediaType.toString();
    }
  }

  public String getResponseMediaType() {
    return responseMediaType;
  }

  public void setResponseMediaType(MediaType mediaType) {
    if (mediaType != null) {
      this.responseMediaType = mediaType.toString();
    }
  }
}
