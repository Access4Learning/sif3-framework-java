package sif3.infra.rest.audit;

import sif3.infra.rest.resource.BaseResource;

/**
 * Auditable Resource
 * Holds the instance of the current resource being audited and a flag indicating if auditing is enabled.
 * This is not the ideal solution but needs to be implemented this way so that auditing is compatible with
 * all web containers and implementations.
 * @author Ben Carter
 */
public class AuditableResource {
  
  // static only.
  private AuditableResource() {
  }
  
  private static final ThreadLocal<BaseResource> resource = new ThreadLocal<BaseResource>();
  private static final ThreadLocal<Boolean> auditing = new ThreadLocal<Boolean>();
  
  public static void setResource(BaseResource baseResource) {
    resource.set(baseResource);
  }
  
  public static BaseResource getResource() {
    return resource.get();
  }
  
  public static boolean isAuditing() {
    return Boolean.TRUE.equals(auditing.get());
  }
  
  public static void setAuditing() {
    auditing.set(Boolean.TRUE);
  }
  
  public static void remove() {
    auditing.remove();
    resource.remove();
  }
}
