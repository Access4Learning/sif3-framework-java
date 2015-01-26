package sif3.infra.rest.audit;

public interface Auditor {
  
  public void audit(AuditRecord auditRecord);

}
