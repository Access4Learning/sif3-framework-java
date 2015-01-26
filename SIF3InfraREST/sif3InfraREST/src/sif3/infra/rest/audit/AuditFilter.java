package sif3.infra.rest.audit;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import sif3.infra.rest.audit.util.AuditRequestWrapper;
import sif3.infra.rest.audit.util.AuditResponseWrapper;

public class AuditFilter implements Filter {

  public static Logger L = LoggerFactory.getLogger(AuditFilter.class);
  public static final String AUDITOR_PROPERTY = "env.audit.class";

  private Auditor auditor = null;

  @Override
  public void destroy() {
    // nothing to do here.
  }

  @Override
  public void init(FilterConfig config) throws ServletException {
    // nothing to do here.
    String className = config.getInitParameter(AUDITOR_PROPERTY);
    if (className != null) {
      try {
        Class<?> auditorClass = Class.forName(className);
        Object instance = null;
        try {
          instance = auditorClass.newInstance();
        } catch (Exception ignore) {
        }
        if (instance != null && Auditor.class.isAssignableFrom(instance.getClass())) {
          auditor = Auditor.class.cast(instance);
        }
        if (auditor != null) {
          L.info("Auditing enabled with : " + className);
        } else {
          L.error("Auditing disabled with : " + className + " - Invalid Type");
        }
      } catch (ClassNotFoundException e) {
        L.error("Auditing disabled with : " + className + " - ClassNotFound", e);
      }
    } else {
      L.info("Auditing disabled");
    }
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
      ServletException {
    if (auditor != null && HttpServletRequest.class.isAssignableFrom(request.getClass())
        && HttpServletResponse.class.isAssignableFrom(response.getClass())) {
      AuditRequestWrapper httpRequest = new AuditRequestWrapper(HttpServletRequest.class.cast(request));
      AuditResponseWrapper httpResponse = new AuditResponseWrapper(HttpServletResponse.class.cast(response));
      AuditableResource.setAuditing();
      try {
        chain.doFilter(httpRequest, httpResponse);
      } catch (Exception ex) {
        L.error("Unhandled exception being thrown :", ex);
      }
      AuditRecord auditRecord = httpRequest.getAuditRecord(httpResponse);
      auditor.audit(auditRecord);
      AuditableResource.remove();
    } else {
      chain.doFilter(request, response);
    }
  }
}
